package com.alchemist.bianca.entity.follow;

import com.alchemist.bianca.entity.user.User;

import java.util.List;

public interface FollowRepositoryCustom {
    List<Follow> getFollowingList(String email);
    List<Follow> getFollowerList(String email);
    void deleteFollow(User follower, User following);
    List<String> getFollowingEmailList(String email);
    Long followingCount(String email);
    Long followerCount(String email);
}
