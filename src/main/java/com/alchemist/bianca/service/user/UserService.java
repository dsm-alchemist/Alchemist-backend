package com.alchemist.bianca.service.user;

import com.alchemist.bianca.dto.user.request.EmailRequest;
import com.alchemist.bianca.dto.user.response.FollowCountResponse;
import com.alchemist.bianca.dto.user.response.UserListResponse;
import com.alchemist.bianca.entity.follow.Follow;
import com.alchemist.bianca.entity.follow.FollowRepository;
import com.alchemist.bianca.entity.user.User;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.UserNotFoundException;
import com.alchemist.bianca.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFacade userFacade;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public ResponseEntity<FollowCountResponse> getFollowCount() {
        int following = followRepository.getFollowingList(userFacade.getEmail()).size();
        int follower = followRepository.getFollowerList(userFacade.getEmail()).size();
        return new ResponseEntity<>(new FollowCountResponse(
                following,
                follower
        ), HttpStatus.OK);
    }

    public ResponseEntity<List<UserListResponse>> getFollowingList() {
        List<UserListResponse> followings = followRepository.getFollowingList(userFacade.getEmail())
                .stream().map(following -> UserListResponse.builder()
                        .userName(following.getFollowing().getName())
                        .userEmail(following.getFollowing().getEmail())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(followings, HttpStatus.OK);
    }

    public ResponseEntity<List<UserListResponse>> getFollowersList() {
        List<UserListResponse> followers = followRepository.getFollowerList(userFacade.getEmail())
                .stream().map(follower -> UserListResponse.builder()
                        .userName(follower.getFollower().getName())
                        .userEmail(follower.getFollower().getEmail())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    public ResponseEntity<List<UserListResponse>> getUsersList() {
        List<UserListResponse> users = userRepository.findAll()
                .stream().map(user -> UserListResponse.builder()
                        .userName(user.getName())
                        .userEmail(user.getEmail())
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
}
