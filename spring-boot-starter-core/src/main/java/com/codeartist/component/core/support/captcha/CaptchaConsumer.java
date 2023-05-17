package com.codeartist.component.core.support.captcha;

import java.util.function.Consumer;

/**
 * 校验验证码是否正确
 *
 * @author 艾江南
 * @date 2022/9/23
 */
@FunctionalInterface
public interface CaptchaConsumer extends Consumer<CaptchaParam> {
}
