package com.codeartist.component.autoconfigure.cache;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.aop.CacheAnnotationAspect;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.metric.CacheMetrics;
import com.codeartist.component.core.support.metric.Metrics;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 缓存组件
 *
 * @author 艾江南
 * @date 2019/4/19
 */
@SpringBootConfiguration
@EnableConfigurationProperties(CacheProperties.class)
@Import({RedisAutoConfiguration.class})
public class CacheAutoConfiguration {

    /**
     * 缓存注解切面
     */
    @Bean
    public CacheAnnotationAspect cacheAnnotationAspect(RedisCacheTemplate redisCacheTemplate) {
        return new CacheAnnotationAspect(redisCacheTemplate);
    }

    @Bean
    public CacheMetrics cacheMetrics(Metrics metrics) {
        return new CacheMetrics(metrics);
    }
}
