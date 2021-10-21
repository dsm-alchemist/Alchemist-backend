package com.alchemist.bianca.dto.task.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserNameResponse {
    private final String name;

    @QueryProjection
    public UserNameResponse(String name) {
        this.name = name;
    }
}
