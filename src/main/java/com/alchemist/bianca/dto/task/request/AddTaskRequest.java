package com.alchemist.bianca.dto.task.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AddTaskRequest {
    @NotNull(message = "task는 null일 수 없습니다")
    private final String task;

    @NotNull(message = "date는 null일 수 없습니다")
    private final LocalDate date;
}
