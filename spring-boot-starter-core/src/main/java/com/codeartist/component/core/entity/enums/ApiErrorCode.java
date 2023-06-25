package com.codeartist.component.core.entity.enums;

import com.codeartist.component.core.api.ICode;
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

    EXPIRE_AUTH_ERROR(100001, "请重新登录"),
    CAPTCHA_VERIFY_ERROR(100002, "验证码校验异常"),
    PRIVATE_API_ERROR(100003, "内部接口禁止访问"),
    SERVICE_BUSY(100004, "请求正在处理中，请稍后"),
    SERVICE_ERROR(100005, "服务器开小差"),
    ;

    private final int code;
    private final String name;
}
