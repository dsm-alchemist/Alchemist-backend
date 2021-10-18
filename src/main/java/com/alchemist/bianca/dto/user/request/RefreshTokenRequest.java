package com.alchemist.bianca.dto.user.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotEmpty(message = "refreshToken은 null이거나 빈 값이 될 수 없습니다.")
    private String refreshToken;
}
