package com.alchemist.bianca.dto.task.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TaskRequest {

    @Email
    private final String Email;

    @NotNull(message = "date는 null일 수 없습니다.")
    private final LocalDate date;
}
