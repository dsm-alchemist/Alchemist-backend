package com.alchemist.bianca.service.user;

import com.alchemist.bianca.dto.user.request.TimerRequest;
import com.alchemist.bianca.dto.user.response.FollowCountResponse;
import com.alchemist.bianca.dto.user.response.LankResponse;
import com.alchemist.bianca.dto.user.response.UserListResponse;
import com.alchemist.bianca.entity.follow.Follow;
import com.alchemist.bianca.entity.follow.FollowRepository;
import com.alchemist.bianca.entity.task.TaskRepository;
import com.alchemist.bianca.entity.user.User;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.UserNotFoundException;
import com.alchemist.bianca.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFacade userFacade;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ResponseEntity<FollowCountResponse> getFollowCount() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();
        String date = format.format(time);
        int following = followRepository.getFollowingList(userFacade.getEmail()).size();
        int follower = followRepository.getFollowerList(userFacade.getEmail()).size();
        int taskCount = taskRepository.findAllByDate(date).size();
        return new ResponseEntity<>(new FollowCountResponse(
                following,
                follower,
                taskCount
        ), HttpStatus.OK);
    }

    public ResponseEntity<List<UserListResponse>> getFollowingList() {
        List<UserListResponse> followings = followRepository.getFollowingList(userFacade.getEmail())
                .stream().map(following -> UserListResponse.builder()
                        .userName(following.getFollowing().getName())
                        .userEmail(following.getFollowing().getEmail())
                        .isFollowing(true)
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(followings, HttpStatus.OK);
    }

    public ResponseEntity<List<UserListResponse>> getFollowersList() {
        List<String> following = followRepository.getFollowingListEmail(userFacade.getEmail());
        List<UserListResponse> followers = followRepository.getFollowerList(userFacade.getEmail())
                .stream().map(follower -> UserListResponse.builder()
                        .userName(follower.getFollower().getName())
                        .userEmail(follower.getFollower().getEmail())
                        .isFollowing(following.contains(follower.getFollower().getEmail()))
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    public ResponseEntity<List<UserListResponse>> getUsersList() {
        List<String> following = followRepository.getFollowingListEmail(userFacade.getEmail());
        List<UserListResponse> users = userRepository.findAll()
                .stream().map(user -> UserListResponse.builder()
                        .userName(user.getName())
                        .userEmail(user.getEmail())
                        .isFollowing(following.contains(user.getEmail()))
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Transactional
    public void addFollowing(String email) {
        User following = userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new);
        User follower = userRepository.findById(userFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        followRepository.save(
                Follow.builder()
                        .following(following)
                        .follower(follower)
                        .build()
        );
    }

    @Transactional
    public void deleteFollowing(String email) {
        User following = userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new);
        User follower = userRepository.findById(userFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        followRepository.deleteFollow(follower, following);
    }

    public ResponseEntity<UserListResponse> myPage() {
        UserListResponse information = userRepository.findById(userFacade.getEmail())
                .map(user -> UserListResponse.builder()
                        .userEmail(user.getEmail())
                        .userName(user.getName())
                        .build())
                .orElseThrow(UserNotFoundException::new);

        return new ResponseEntity<>(information, HttpStatus.OK);
    }

    @Transactional
    public void startTimer(TimerRequest time) {
        userRepository.startTimer(time.getTime());
    }

    @Transactional
    public void stopTimer() {
        userRepository.stopTimer();
    }

    public Page<LankResponse> rank(Pageable pageable) {
        return userRepository.rank(pageable);
    }
}
