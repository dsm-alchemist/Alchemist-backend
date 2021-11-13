package com.alchemist.bianca.dto.user.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LankResponse {
    private String name;
    private Long timer;
    private Boolean is_stop;

    @QueryProjection
    public LankResponse(String name, Long timer, Boolean is_stop) {
        this.name = name;
        this.timer = timer;
        this.is_stop = is_stop;
    }
}
