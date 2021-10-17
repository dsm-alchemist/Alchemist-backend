package com.alchemist.bianca.dto.user.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;
}
