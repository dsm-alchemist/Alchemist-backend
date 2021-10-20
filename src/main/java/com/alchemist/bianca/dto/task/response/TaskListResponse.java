package com.alchemist.bianca.dto.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class TaskListResponse {
    private final String userName;
    private final List<TaskList> taskList;
}
