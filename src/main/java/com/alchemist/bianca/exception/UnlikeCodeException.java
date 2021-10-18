package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class UnlikeCodeException extends ServerException {
    public UnlikeCodeException() {
        super(ErrorCode.UNLIKE_CODE);
    }
}
