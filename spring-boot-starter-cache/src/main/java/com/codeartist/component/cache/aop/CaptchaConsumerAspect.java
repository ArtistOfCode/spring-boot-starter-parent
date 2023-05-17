package com.codeartist.component.cache.aop;

import com.codeartist.component.core.support.captcha.CaptchaConsumer;
import com.codeartist.component.core.support.captcha.CaptchaParam;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 验证码校验切片
 *
 * @author 艾江南
 * @since 2023-03-08
 */
@Aspect
@RequiredArgsConstructor
public class CaptchaConsumerAspect {

    private final CaptchaConsumer captchaConsumer;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController() {
    }

    @Before("restController() && args(param,..)")
    public void doBefore(CaptchaParam param) {
        captchaConsumer.accept(param);
    }
}
