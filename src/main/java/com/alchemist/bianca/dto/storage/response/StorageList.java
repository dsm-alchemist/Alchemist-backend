package com.alchemist.bianca.dto.storage.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class StorageList {

    private final Long storage_id;
    private final String task;

    @QueryProjection
    public StorageList(Long storage_id, String task) {
        this.storage_id = storage_id;
        this.task = task;
    }
}
