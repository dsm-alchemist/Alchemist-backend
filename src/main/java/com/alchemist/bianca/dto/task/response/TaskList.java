package com.alchemist.bianca.dto.task.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TaskList {
    private final Long task_id;
    private final String task;
    private final boolean isDone;

    @QueryProjection
    public TaskList(Long task_id,String task, boolean isDone) {
        this.task_id = task_id;
        this.task = task;
        this.isDone = isDone;
    }
}
