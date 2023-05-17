package com.codeartist.component.autoconfigure.captcha;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.aop.CaptchaConsumerAspect;
import com.codeartist.component.core.support.captcha.CaptchaConsumer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * 验证码校验配置
 *
 * @author 艾江南
 * @date 2022/9/23
 */
@SpringBootConfiguration
@ConditionalOnBean(RedisCacheTemplate.class)
public class CaptchaAutoConfiguration {

    @Bean
    public CaptchaConsumer redisCaptchaConsumer(RedisCacheTemplate redisCacheTemplate) {
        return new RedisCaptchaConsumer(redisCacheTemplate);
    }

    @Bean
    public CaptchaConsumerAspect captchaConsumerAspect(CaptchaConsumer captchaConsumer) {
        return new CaptchaConsumerAspect(captchaConsumer);
    }
}
