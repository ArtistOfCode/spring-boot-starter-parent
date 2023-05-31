package com.codeartist.component.core.exception;

import com.codeartist.component.core.api.ICode;

/**
 * 业务异常，返回客户端异常消息，warn 级别日志
 *
 * @author 艾江南
 * @date 2020/9/8
 */
public class BusinessException extends RuntimeException {

    private ICode code;

    public BusinessException() {
        super();
    }

    public BusinessException(ICode code) {
        super(code.getName());
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ICode getCode() {
        return code;
    }
}
