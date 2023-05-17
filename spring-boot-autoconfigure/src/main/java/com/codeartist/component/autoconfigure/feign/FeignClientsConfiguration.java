package com.codeartist.component.autoconfigure.feign;

import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * Feign的配配置
 *
 * @author 艾江南
 * @date 2022/11/3
 */
@ConditionalOnClass(Feign.class)
public class FeignClientsConfiguration {

    @Bean
    public ErrorDecoder feignRpcErrorDecoder() {
        return new FeignRpcErrorDecoder();
    }
}
