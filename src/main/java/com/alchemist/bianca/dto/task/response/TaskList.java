package com.alchemist.bianca.dto.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TaskList {
    private final String task;
    private final boolean isDone;
}
