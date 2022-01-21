package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class UnexpectedException extends ServerException {
    public UnexpectedException() {
        super(ErrorCode.UNEXPECTED_ERROR);
    }
}
