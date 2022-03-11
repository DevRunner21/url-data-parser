package com.devrun.urldataparser.common.dto;

import com.devrun.urldataparser.common.exception.ErrorInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean success;

    private final T data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime serverDatetime;

    protected ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
        this.serverDatetime = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> ok(T response) {
        return new ApiResponse<>(true, response);
    }

    public static ApiResponse<ApiError> error(ErrorInfo errorInfo) {
        return new ApiResponse<>(false, ApiError.of(errorInfo));
    }

    public static ApiResponse<ApiError> error(String code, Object messages) {
        return new ApiResponse<>(false, ApiError.of(code, messages));
    }

}
