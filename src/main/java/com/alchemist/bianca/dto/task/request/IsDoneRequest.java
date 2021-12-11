package com.alchemist.bianca.dto.task.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class IsDoneRequest {
    @NotNull
    private Boolean isDone;
}
