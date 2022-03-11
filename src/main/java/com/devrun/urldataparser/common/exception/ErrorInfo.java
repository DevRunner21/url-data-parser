package com.devrun.urldataparser.common.exception;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    UNKNOWN("UNKNOWN", "서버 에러로 인해 데이터를 로드 할 수 없습니다."),
    METHOD_ARG_NOT_VALID("METHOD_ARG_NOT_VALID", "");

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

}
