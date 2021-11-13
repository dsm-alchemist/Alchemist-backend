package com.alchemist.bianca.entity.user;

import com.alchemist.bianca.dto.user.response.LankResponse;
import com.alchemist.bianca.dto.user.response.QLankResponse;
import com.alchemist.bianca.facade.UserFacade;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.alchemist.bianca.entity.follow.QFollow.follow;
import static com.alchemist.bianca.entity.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final UserFacade userFacade;

    @Override
    public void startTimer(Long time) {
        queryFactory
                .update(user)
                .set(user.timer, time)
                .set(user.is_stop, false)
                .where(user.email.eq(userFacade.getEmail()))
                .execute();
    }

    @Override
    public void stopTimer() {
        queryFactory
                .update(user)
                .set(user.is_stop, true)
                .where(user.email.eq(userFacade.getEmail()))
                .execute();
    }

    @Override
    public Page<LankResponse> lank(Pageable pageable) {
        User us = queryFactory
                .selectFrom(user)
                .where(user.email.eq(userFacade.getEmail()))
                .fetchOne();

        QueryResults<LankResponse> results = queryFactory
                .select(new QLankResponse(
                        user.name,
                        user.timer,
                        user.is_stop
                ))
                .from(user)
                .join(user.follower, follow)
                .where(follow.follower.eq(us))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<LankResponse> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
