package com.alchemist.bianca.dto.storage.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class StorageRequest {

    @NotNull(message = "task는 null일 수 없습니다")
    private String task;
}
