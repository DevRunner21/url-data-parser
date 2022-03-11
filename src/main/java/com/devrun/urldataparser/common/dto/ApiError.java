package com.devrun.urldataparser.common.dto;

import com.devrun.urldataparser.common.exception.ErrorInfo;
import lombok.Getter;

@Getter
public class ApiError {

    private final String code;

    private final Object message;

    private ApiError(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    static ApiError of(ErrorInfo errorInfo) {
        return new ApiError(errorInfo.getCode(), errorInfo.getMessage());
    }

    static ApiError of(String code, Object messages) {
        return new ApiError(code, messages);
    }

}
