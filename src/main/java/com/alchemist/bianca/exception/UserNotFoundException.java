package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class UserNotFoundException extends ServerException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
