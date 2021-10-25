package com.alchemist.bianca.entity.storage;

import com.alchemist.bianca.dto.storage.request.StorageRequest;
import com.alchemist.bianca.dto.storage.response.QStorageList;
import com.alchemist.bianca.dto.storage.response.StorageList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.alchemist.bianca.entity.storage.QStorage.*;
import static com.alchemist.bianca.entity.user.QUser.*;

@RequiredArgsConstructor
public class StorageRepositoryCustomImpl implements StorageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StorageList> getStorageList(String email) {
        return queryFactory
                .select(new QStorageList(
                        storage.storage_id,
                        storage.task
                ))
                .from(storage)
                .join(storage.email, user)
                .where(user.email.eq(email))
                .fetch();
    }

    @Override
    public void modifyStorage(Long storage_id, StorageRequest task) {
        queryFactory
                .update(storage)
                .set(storage.task, task.getTask())
                .where(storage.storage_id.eq(storage_id))
                .execute();
    }

    @Override
    public void deleteStorage(Long storage_id) {
        queryFactory
                .delete(storage)
                .where(storage.storage_id.eq(storage_id))
                .execute();
    }
}
