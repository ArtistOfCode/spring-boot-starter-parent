package com.codeartist.component.autoconfigure.captcha;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.core.entity.enums.ApiErrorCode;
import com.codeartist.component.core.entity.enums.GlobalConstants.RedisKey;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.core.support.captcha.CaptchaConsumer;
import com.codeartist.component.core.support.captcha.CaptchaParam;
import com.codeartist.component.core.util.Assert;

import java.util.Objects;

/**
 * Redis校验验证码
 *
 * @author 艾江南
 * @date 2022/9/23
 */
public class RedisCaptchaConsumer implements CaptchaConsumer {

    private final RedisCacheTemplate redisCacheTemplate;

    public RedisCaptchaConsumer(RedisCacheTemplate redisCacheTemplate) {
        this.redisCacheTemplate = redisCacheTemplate;
    }

    @Override
    public void accept(CaptchaParam param) {
        Assert.notBlank(param.getKey(), "验证Key不能为空");
        Assert.notBlank(param.getCode(), "验证码不能为空");

        String actualCode = redisCacheTemplate.get(RedisKey.CAPTCHA_KEY + param.getKey(), String.class);
        if (Objects.equals(actualCode, param.getCode())) {
            redisCacheTemplate.delete(RedisKey.CAPTCHA_KEY + param.getKey());
            return;
        }
        throw new BusinessException(ApiErrorCode.CAPTCHA_VERIFY_ERROR);
    }
}
