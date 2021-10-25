package com.alchemist.bianca.entity.storage;

import com.alchemist.bianca.dto.storage.request.StorageRequest;
import com.alchemist.bianca.dto.storage.response.StorageList;

import java.util.List;

public interface StorageRepositoryCustom {
    List<StorageList> getStorageList(String email);
    void modifyStorage(Long storage_id, StorageRequest task);
    void deleteStorage(Long storage_id);
}
