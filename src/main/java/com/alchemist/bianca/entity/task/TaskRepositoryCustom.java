package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.dto.task.response.TaskList;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepositoryCustom {
    List<TaskList> getTaskList(String userEmail, LocalDate date);
    void modifyTask(Long task_id, String task);
    void modifyDate(Long task_id, LocalDate date);
    void deleteTask(Long task_id);
}
