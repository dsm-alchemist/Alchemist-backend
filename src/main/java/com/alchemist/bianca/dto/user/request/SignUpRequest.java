package com.alchemist.bianca.dto.user.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String user_name;
}
