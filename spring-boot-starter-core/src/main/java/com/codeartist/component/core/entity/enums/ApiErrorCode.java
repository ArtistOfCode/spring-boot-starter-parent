package com.codeartist.component.core.entity.enums;

import com.codeartist.component.core.entity.ICode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 接口响应枚举
 *
 * @author 艾江南
 * @date 2020/9/11
 */
@Getter
@AllArgsConstructor
public enum ApiErrorCode implements ICode {

    EXPIRE_AUTH_ERROR(401, "请重新登录"),
    CAPTCHA_VERIFY_ERROR(401, "验证码校验异常"),
    PRIVATE_API_ERROR(403, "内部接口禁止访问"),
    SERVICE_BUSY(503, "请求正在处理中，请稍后"),
    SERVICE_ERROR(500, "服务器开小差"),
    ;

    private final int code;
    private final String name;
}
