package com.codeartist.component.core.exception;

import com.codeartist.component.core.entity.ResponseError;

/**
 * Feign调用异常，返回客户端异常消息，warn 级别日志
 *
 * @author 艾江南
 * @date 2022/7/27
 */
public class FeignException extends RuntimeException {

    private ResponseError responseError;

    public FeignException(ResponseError responseError) {
        super(responseError.getMessage());
        this.responseError = responseError;
    }

    public FeignException(String message) {
        super(message);
    }

    public FeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignException(Throwable cause) {
        super(cause);
    }

    public FeignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseError getResponseError() {
        return responseError;
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }
}
