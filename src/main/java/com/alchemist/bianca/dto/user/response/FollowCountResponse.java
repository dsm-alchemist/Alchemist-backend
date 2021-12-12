package com.alchemist.bianca.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowCountResponse {
    private final int following;
    private final int follower;
    private final int taskCount;
}
