package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class InvalidCodeException extends ServerException {
    public InvalidCodeException() {
        super(ErrorCode.INVALID_CODE);
    }
}
