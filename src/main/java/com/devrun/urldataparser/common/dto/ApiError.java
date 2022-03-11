package com.devrun.urldataparser.common.dto;

import com.devrun.urldataparser.common.exception.ErrorInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiError {

    @ApiModelProperty(value = "에러코드", dataType = "string", example = "ERR001")
    private final String code;

    @ApiModelProperty(value = "에러메시지", dataType = "string", example = "에러메시지")
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
