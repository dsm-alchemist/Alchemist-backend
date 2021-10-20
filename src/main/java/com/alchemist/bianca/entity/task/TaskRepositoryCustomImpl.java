package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.dto.task.request.TaskRequest;
import com.alchemist.bianca.dto.task.response.QTaskList;
import com.alchemist.bianca.dto.task.response.TaskList;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.alchemist.bianca.entity.task.QTask.task1;
import static com.alchemist.bianca.entity.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TaskList> getTaskList(TaskRequest request) {
        return queryFactory
                .select(new QTaskList(
                        task1.task_id,
                        task1.task,
                        task1.isDone
                ))
                .from(task1)
                .join(task1.email, user)
                .where(
                        emailEq(request.getEmail()),
                        task1.date.eq(request.getDate())
                )
                .fetch();
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? user.email.eq(email) : null;
    }
}
