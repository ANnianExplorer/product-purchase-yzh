package com.yzh.exception;


/**
 * 业务异常
 *
 * @author 杨振华
 * @date 2022/08/15
 */
public class BusinessException extends RuntimeException {

    private final BusinessCode businessCode;
    private final String message;

    public BusinessException(BusinessCode businessCode, String message) {
        this.businessCode = businessCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessCode getBusinessCode() {
        return businessCode;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
