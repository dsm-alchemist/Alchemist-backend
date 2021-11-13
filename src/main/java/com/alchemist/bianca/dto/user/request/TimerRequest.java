package com.alchemist.bianca.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class TimerRequest {

    @NotNull(message = "time은 null일 수 없습니다.")
    private Long time;
}
