package com.alchemist.bianca.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND(404, "user not found"),
    INVALID_TOKEN(401, "Invalid token"),
    EXPIRED_ACCESS_TOKEN(401, "Expired access token"),
    EXPIRED_REFRESH_TOKEN(401, "Expired refresh token"),
    MISMATCHED_PASSWORD(400, "Mismatched password"),
    ALREADY_EXIST_EMAIL(400, "Already Exist Email"),
    ALREADY_EXIST_NAME(400, "Already Exist Name"),
    INVALID_CODE(400, "Invalid code"),
    UNLIKE_CODE(400, "Unlike code"),
    CREDENTIALS_NOT_FOUND(401, "Credentials not found");

    private final int status;
    private final String message;
}
