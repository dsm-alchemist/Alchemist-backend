package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class FileSaveFailedException extends ServerException {
    public FileSaveFailedException() {
        super(ErrorCode.FILE_SAVE_FAILED);
    }
}
