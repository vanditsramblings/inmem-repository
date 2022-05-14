package com.rambler.inmem.exception;

public enum ErrorCode {
    REPOSITORY_CONFIGURATION_ERROR(1001),
    REPOSITORY_MEMORY_ERROR(1002),
    UNKNOWN_ERROR(1005);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
