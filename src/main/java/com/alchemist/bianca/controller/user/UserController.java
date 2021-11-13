package com.alchemist.bianca.controller.user;

import com.alchemist.bianca.dto.task.response.TaskListResponse;
import com.alchemist.bianca.dto.user.request.EmailRequest;
import com.alchemist.bianca.dto.user.request.TimerRequest;
import com.alchemist.bianca.dto.user.response.FollowCountResponse;
import com.alchemist.bianca.dto.user.response.UserListResponse;
import com.alchemist.bianca.service.task.TaskService;
import com.alchemist.bianca.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/follow")
    public ResponseEntity<FollowCountResponse> getFollowCount() {
        return userService.getFollowCount();
    }

    @GetMapping("/following")
    public ResponseEntity<List<UserListResponse>> getFollowingList() {
        return userService.getFollowingList();
    }

    @GetMapping("/followers")
    public ResponseEntity<List<UserListResponse>> getFollowersList() {
        return userService.getFollowersList();
    }

    @GetMapping("/explore")
    public ResponseEntity<List<UserListResponse>> getUsersList() {
        return userService.getUsersList();
    }

    @PostMapping("/following/{userEmail}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFollowing(@PathVariable("userEmail") String email) {
        userService.addFollowing(email);
    }

    @DeleteMapping("/following/{userEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollowing(@PathVariable("userEmail") String email) {
        userService.deleteFollowing(email);
    }

    @GetMapping("/task/{userEmail}")
    public ResponseEntity<TaskListResponse> getOtherTaskList(@PathVariable String userEmail, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taskService.getOtherTaskList(userEmail, date);
    }

    @GetMapping("/account")
    public ResponseEntity<UserListResponse> myPage() {
        return userService.myPage();
    }

    @PostMapping("/timer")
    public void startTimer(@RequestBody @Valid TimerRequest time) {
        userService.startTimer(time);
    }

    @PutMapping("/timer")
    public void stopTimer() {
        userService.stopTimer();
    }
}
