package com.alchemist.bianca.dto.task.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class TaskRequest {

    @NotNull(message = "task는 null일 수 없습니다")
    private String task;

}
