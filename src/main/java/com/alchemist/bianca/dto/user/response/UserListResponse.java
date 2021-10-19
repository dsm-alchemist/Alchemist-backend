package com.alchemist.bianca.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserListResponse {
    private final String userName;
    private final String userEmail;
}
