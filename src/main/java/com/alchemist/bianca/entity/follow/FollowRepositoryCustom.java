package com.alchemist.bianca.entity.follow;

import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.TaskList;

import java.time.LocalDate;
import java.util.List;

public interface FollowRepositoryCustom {
    List<TaskList> getTaskList(TaskRequest request);
}
