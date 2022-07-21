package dev.practice.order.common.response;

import lombok.Getter;

@Getter
public enum ErrorCode {

    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다.");

    private final String errorMsg;

    ErrorCode(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
