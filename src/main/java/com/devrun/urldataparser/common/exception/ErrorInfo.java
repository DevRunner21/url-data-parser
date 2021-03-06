package com.devrun.urldataparser.common.exception;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    UNKNOWN("UNKNOWN", "서버 에러로 인해 데이터를 로드 할 수 없습니다."),
    METHOD_ARG_NOT_VALID("METHOD_ARG_NOT_VALID", ""),
    INVALID_URL("INVALID_URL", "URL에 해당하는 데이터를 가져 올 수 없습니다."),
    UNIT_OVERFLOW("UNIT_OVERFLOW", "출력단위가 너무 큽니다.");

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

}
