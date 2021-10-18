package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class AlreadyExistEmailException extends ServerException {

    public AlreadyExistEmailException(){
        super(ErrorCode.ALREADY_EXIST_EMAIL);
    }
}
