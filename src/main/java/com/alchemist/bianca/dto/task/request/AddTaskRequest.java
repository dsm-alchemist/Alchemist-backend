package com.alchemist.bianca.dto.task.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AddTaskRequest {
    @NotNull(message = "task는 null일 수 없습니다")
    private String task;

    @NotNull(message = "date는 null일 수 없습니다")
    private LocalDate date;
}
