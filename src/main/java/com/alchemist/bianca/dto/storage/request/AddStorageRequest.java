package com.alchemist.bianca.dto.storage.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AddStorageRequest {

    @NotNull(message = "task는 null일 수 없습니다")
    private final String task;
}
