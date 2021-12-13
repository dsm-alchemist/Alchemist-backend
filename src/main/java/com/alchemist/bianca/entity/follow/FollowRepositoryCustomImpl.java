package com.alchemist.bianca.entity.follow;

import com.alchemist.bianca.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.alchemist.bianca.entity.follow.QFollow.*;
import static com.alchemist.bianca.entity.user.QUser.*;

@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Follow> getFollowerList(String email) {
        return queryFactory
                .selectFrom(follow)
                .join(follow.following, user)
                .where(user.email.eq(email))
                .fetch();
    }

    @Override
    public List<Follow> getFollowingList(String email) {
        return queryFactory
                .selectFrom(follow)
                .join(follow.follower, user)
                .where(user.email.eq(email))
                .fetch();
    }

    @Override
    public void deleteFollow(User follower, User following) {
        queryFactory
                .delete(follow)
                .where(
                        follow.follower.eq(follower),
                        follow.following.eq(following)
                )
                .execute();
    }

    @Override
    public List<String> getFollowingListEmail(String email) {
        return queryFactory
                .select(follow.following.email)
                .from(follow)
                .join(follow.follower, user)
                .where(user.email.eq(email))
                .fetch();
    }
}
