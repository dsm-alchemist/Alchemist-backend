package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class CredentialsNotFoundException extends ServerException {
    public CredentialsNotFoundException() {
        super(ErrorCode.CREDENTIALS_NOT_FOUND);
    }
}
