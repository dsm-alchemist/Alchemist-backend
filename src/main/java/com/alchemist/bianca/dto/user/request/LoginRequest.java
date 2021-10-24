package com.alchemist.bianca.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "email은 null일 수 없습니다.")
    @Email
    private String email;

    @NotNull(message = "password는 null일 수 없습니다.")
    private String password;
}
