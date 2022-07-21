package dev.practice.order.common.exception;

import dev.practice.order.common.response.ErrorCode;

public class BaseException extends RuntimeException {

    private ErrorCode errorCode;

    public BaseException() {}

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
