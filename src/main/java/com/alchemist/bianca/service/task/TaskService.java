package com.alchemist.bianca.service.task;

import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.OtherTaskListResponse;
import com.alchemist.bianca.dto.task.response.TaskList;
import com.alchemist.bianca.entity.task.Task;
import com.alchemist.bianca.entity.task.TaskRepository;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public OtherTaskListResponse getOtherTaskList(TaskRequest request) {
        userRepository.findById(request.getEmail())
                .map(user -> OtherTaskListResponse.builder()
                        .userName(user.getName())
                        .build())
                .orElseThrow(UserNotFoundException::new);

        List<TaskList> taskList = taskRepository.findAllByDate(request.getDate())
                .stream()
                .sorted(Comparator.comparing(Task::getTask_id))
                .map(tasks -> TaskList.builder()
                        .task(tasks.getTask())
                        .isDone(tasks.isDone())
                        .build())
                .collect(Collectors.toList());

        return OtherTaskListResponse.builder()
                .taskList(taskList)
                .build();
    }
}
