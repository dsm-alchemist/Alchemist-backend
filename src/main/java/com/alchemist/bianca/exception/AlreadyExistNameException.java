package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class AlreadyExistNameException extends ServerException {
    public AlreadyExistNameException() {
        super(ErrorCode.ALREADY_EXIST_NAME);
    }
}
