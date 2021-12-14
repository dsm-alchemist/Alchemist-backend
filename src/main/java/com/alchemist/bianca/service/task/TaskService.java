package com.alchemist.bianca.service.task;

import com.alchemist.bianca.dto.task.request.AddTaskRequest;
import com.alchemist.bianca.dto.task.request.IsDoneRequest;
import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.TaskListResponse;
import com.alchemist.bianca.dto.task.response.TaskList;
import com.alchemist.bianca.entity.storage.Storage;
import com.alchemist.bianca.entity.storage.StorageRepository;
import com.alchemist.bianca.entity.task.Task;
import com.alchemist.bianca.entity.task.TaskRepository;
import com.alchemist.bianca.entity.user.User;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.TaskNotFoundException;
import com.alchemist.bianca.exception.UserNotFoundException;
import com.alchemist.bianca.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final StorageRepository storageRepository;
    private final UserFacade userFacade;

    public void addTask(AddTaskRequest request) {
        User user = userRepository.findById(userFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        taskRepository.save(
                Task.builder()
                        .task(request.getTask())
                        .date(request.getDate())
                        .email(user)
                        .build()
        );
    }

    @Transactional
    public ResponseEntity<TaskListResponse> getOtherTaskList(String email, String date) {
        String name = getName(email);

        List<TaskList> taskList = taskRepository.getTaskList(email, date);

        return new ResponseEntity<>(
                TaskListResponse.builder()
                        .userName(name)
                        .taskList(taskList)
                        .build(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<TaskListResponse> getMyTaskList(String date) {
        String name = getName(userFacade.getEmail());

        List<TaskList> taskList = taskRepository.getTaskList(null, date);

        return new ResponseEntity<>(
                TaskListResponse.builder()
                        .userName(name)
                        .taskList(taskList)
                        .build(), HttpStatus.OK);
    }

    @Transactional
    public void modifyTask(Long task_id, TaskRequest task) {
        taskRepository.modifyTask(task_id, task);
    }

    @Transactional
    public void modifyDate(Long task_id, String date) {
        taskRepository.modifyDate(task_id, date);
    }

    @Transactional
    public void deleteTask(Long task_id) {
        taskRepository.deleteTask(task_id);
    }

    @Transactional
    public void moveTaskToStorage(Long task_id) {
        Task task = taskRepository.findById(task_id)
                .orElseThrow(TaskNotFoundException::new);

        storageRepository.save(
                Storage.builder()
                        .task(task.getTask())
                        .email(task.getEmail())
                        .build()
        );

        taskRepository.delete(task);
    }

    @Transactional
    public void isDone(Long task_id, IsDoneRequest is_done) {
        taskRepository.modifyIsDone(task_id, is_done);
    }

    private String getName(String email) {
        return userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new)
                .getName();
    }
}
