package com.alchemist.bianca.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowCountResponse {
    private final Long following;
    private final Long follower;
    private final Long taskCount;
}
