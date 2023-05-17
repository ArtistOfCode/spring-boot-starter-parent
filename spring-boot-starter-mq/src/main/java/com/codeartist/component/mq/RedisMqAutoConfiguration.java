package com.codeartist.component.mq;

import com.codeartist.component.mq.core.MqConsumer;
import com.codeartist.component.mq.core.MqProducer;
import com.codeartist.component.mq.core.redismq.RedisMqConsumerContainer;
import com.codeartist.component.mq.core.redismq.RedisMqProducer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * RedisMQ配置
 *
 * @author J.N.AI
 * @date 2023/5/15
 */
@SpringBootConfiguration
public class RedisMqAutoConfiguration {

    @Bean
    @Lazy
    public MqProducer redisMqProducer(StringRedisTemplate stringRedisTemplate) {
        return new RedisMqProducer(stringRedisTemplate);
    }

    @Bean
    public MqConsumer redisMqConsumerContainer(StringRedisTemplate stringRedisTemplate) {
        return new RedisMqConsumerContainer(stringRedisTemplate);
    }
}
