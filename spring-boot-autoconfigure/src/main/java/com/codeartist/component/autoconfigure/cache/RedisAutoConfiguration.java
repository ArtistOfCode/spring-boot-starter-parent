package com.codeartist.component.autoconfigure.cache;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.metric.CacheMetrics;
import com.codeartist.component.cache.support.redis.NoopRedisCacheTemplateImpl;
import com.codeartist.component.cache.support.redis.RedisCacheTemplateImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Optional;

/**
 * Redis自动配置
 *
 * @author 艾江南
 * @date 2022/12/1
 */
@SpringBootConfiguration
@Import(RedisAutoConfiguration.KeyExpirationAutoConfiguration.class)
public class RedisAutoConfiguration {

    /**
     * Redis缓存客户端工具
     */
    @Bean
    public RedisCacheTemplate redisCacheTemplate(CacheProperties cacheProperties, CacheMetrics cacheMetrics,
                                                 StringRedisTemplate stringRedisTemplate) {
        boolean enableRedis = Optional.ofNullable(cacheProperties.getRedis())
                .map(CacheProperties.Redis::isEnabled)
                .orElse(true);
        return enableRedis ?
                new RedisCacheTemplateImpl(stringRedisTemplate, cacheProperties, cacheMetrics) :
                new NoopRedisCacheTemplateImpl(cacheProperties, cacheMetrics);
    }

    @SpringBootConfiguration
    public static class KeyExpirationAutoConfiguration {

        /**
         * Redis缓存监听容器
         */
        @Bean
        @ConditionalOnBean(LettuceConnectionFactory.class)
        public RedisMessageListenerContainer redisMessageListenerContainer(LettuceConnectionFactory lettuceConnectionFactory) {
            RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(lettuceConnectionFactory);
            return container;
        }

        /**
         * Redis键过期监听器
         */
        @Bean
        @ConditionalOnBean(RedisMessageListenerContainer.class)
        public KeyExpirationEventMessageListener keyExpirationEventMessageListener(RedisMessageListenerContainer container) {
            return new KeyExpirationEventMessageListener(container);
        }
    }
}
