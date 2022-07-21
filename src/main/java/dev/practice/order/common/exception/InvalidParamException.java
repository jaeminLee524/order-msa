package dev.practice.order.common.exception;

import dev.practice.order.common.response.ErrorCode;

public class InvalidParamException extends BaseException {

    public InvalidParamException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
