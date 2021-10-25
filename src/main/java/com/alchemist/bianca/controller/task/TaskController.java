package com.alchemist.bianca.controller.task;

import com.alchemist.bianca.dto.task.request.AddTaskRequest;
import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.TaskListResponse;
import com.alchemist.bianca.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskListResponse> getTaskList(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taskService.getMyTaskList(date);
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyTask(@PathVariable("taskId") Long task_id, @RequestBody TaskRequest task) {
        taskService.modifyTask(task_id, task);
    }

    @PostMapping("/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyDate(@PathVariable("taskId") Long task_id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        taskService.modifyDate(task_id, date);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("taskId") Long task_id) {
        taskService.deleteTask(task_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody AddTaskRequest request) {
        taskService.addTask(request);
    }

    @PostMapping("/{taskId}/storage")
    @ResponseStatus(HttpStatus.CREATED)
    public void moveTaskToStorage(@PathVariable("taskId") Long task_id) {
        taskService.moveTaskToStorage(task_id);
    }
}
