package com.alchemist.bianca.exception;

import com.alchemist.bianca.error.exception.ErrorCode;
import com.alchemist.bianca.error.exception.ServerException;

public class TaskNotFoundException extends ServerException {
    public TaskNotFoundException() {
        super(ErrorCode.TASK_NOT_FOUND);
    }
}
