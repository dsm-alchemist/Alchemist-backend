package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class InvalidTokenException extends ServerException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
