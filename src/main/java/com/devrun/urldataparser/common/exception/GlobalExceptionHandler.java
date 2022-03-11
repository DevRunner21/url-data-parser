package com.devrun.urldataparser.common.exception;

import com.devrun.urldataparser.common.dto.ApiError;
import com.devrun.urldataparser.common.dto.ApiResponse;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ApiError> handleBindException(BindException ex) {
        return ApiResponse.error(ErrorInfo.METHOD_ARG_NOT_VALID.getCode(), ex.getBindingResult().getAllErrors().stream()
            .collect(Collectors.toMap(error -> ((FieldError) error).getField(), ObjectError::getDefaultMessage)));
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ApiError> handleBusinessExceptionException(BusinessException ex) {
        return ApiResponse.error(ex.getErrorInfo());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ApiError> handleException(Exception ex) {
        return ApiResponse.error(ErrorInfo.UNKNOWN);
    }

}
