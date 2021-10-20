package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.TaskList;

import java.util.List;

public interface TaskRepositoryCustom {
    List<TaskList> getTaskList(TaskRequest request);
}
