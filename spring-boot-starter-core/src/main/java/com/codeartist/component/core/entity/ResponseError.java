package com.codeartist.component.core.entity;

import com.codeartist.component.core.api.ICode;
import lombok.Getter;
import lombok.Setter;

/**
 * HTTP 接口响应异常实体
 *
 * @author 艾江南
 * @date 2022/7/22
 */
@Getter
@Setter
public class ResponseError {

    private int code;
    private String message;
    private String stackTrace;

    public ResponseError() {
    }

    public ResponseError(ICode code) {
        this(code.getCode(), code.getName());
    }

    public ResponseError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
