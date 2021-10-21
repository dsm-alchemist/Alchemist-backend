package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class StorageNotFoundException extends ServerException {
    public StorageNotFoundException() {
        super(ErrorCode.STORAGE_NOT_FOUND);
    }
}
