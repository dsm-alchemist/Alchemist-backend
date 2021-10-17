package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class MismatchedPassword extends ServerException {
    public MismatchedPassword() {
        super(ErrorCode.MISMATCHED_PASSWORD);
    }
}
