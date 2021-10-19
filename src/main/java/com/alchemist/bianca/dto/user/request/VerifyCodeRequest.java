package com.alchemist.bianca.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class VerifyCodeRequest {

    @NotNull(message = "code는 null일 수 없습니다.")
    private String code;
}
