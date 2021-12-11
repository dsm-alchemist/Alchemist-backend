package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.dto.task.request.IsDoneRequest;
import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.TaskList;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepositoryCustom {
    List<TaskList> getTaskList(String userEmail, String date);
    void modifyTask(Long task_id, TaskRequest task);
    void modifyDate(Long task_id, String date);
    void deleteTask(Long task_id);
    void modifyIsDone(Long task_id, IsDoneRequest is_done);
}
