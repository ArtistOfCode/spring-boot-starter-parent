package com.codeartist.component.sample.test.cache;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.core.entity.enums.ApiErrorCode;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.sample.entity.Example;
import com.codeartist.component.sample.entity.GenericExample;
import com.codeartist.component.sample.service.CacheService;
import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

/**
 * 缓存组件测试
 *
 * @author 艾江南
 * @date 2021/7/23
 */
public class RedisCacheComponentTest extends AbstractSpringRunnerTests {

    private final Duration expire = Duration.ofMinutes(3);

    @Autowired
    private RedisCacheTemplate redisCacheTemplate;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private ThreadPoolTaskExecutor applicationTaskExecutor;

    @Test
    public void redisStringCache() {
        String key = UUID.randomUUID().toString();
        String val = UUID.randomUUID().toString();

        String result = redisCacheTemplate.get(key, String.class);
        Assertions.assertNull(result);

        redisCacheTemplate.set(key, val, expire);
        result = redisCacheTemplate.get(key, String.class);
        Assertions.assertEquals(val, result);

        redisCacheTemplate.set(key, null, expire);
        result = redisCacheTemplate.get(key, String.class);
        Assertions.assertNull(result);

        redisCacheTemplate.delete(key);

        result = redisCacheTemplate.get(key, String.class);
        Assertions.assertNull(result);
    }

    @Test
    public void redisHashCache() {
        String key = UUID.randomUUID().toString();
        String hashKey = UUID.randomUUID().toString();
        String val = UUID.randomUUID().toString();

        String result = redisCacheTemplate.get(key, hashKey, String.class);
        Assertions.assertNull(result);

        redisCacheTemplate.set(key, hashKey, expire, val);
        result = redisCacheTemplate.get(key, hashKey, String.class);
        Assertions.assertEquals(val, result);

        redisCacheTemplate.set(key, hashKey, expire, null);
        result = redisCacheTemplate.get(key, hashKey, String.class);
        Assertions.assertNull(result);

        redisCacheTemplate.delete(key, hashKey);

        result = redisCacheTemplate.get(key, hashKey, String.class);
        Assertions.assertNull(result);
    }

    @Test
    public void redisOther() {
        // Inc
        String key = UUID.randomUUID().toString();
        redisCacheTemplate.inc(key);
        redisCacheTemplate.inc(key);
        long inc = redisCacheTemplate.inc(key);
        Assertions.assertEquals(3, inc);

        // Has
        boolean hasKey = redisCacheTemplate.exist(key);
        Assertions.assertTrue(hasKey);

        redisCacheTemplate.expire(key, Duration.ofSeconds(30));

        redisCacheTemplate.delete(key);

        hasKey = redisCacheTemplate.exist(key);
        Assertions.assertFalse(hasKey);
    }

    @RepeatedTest(10)
    public void redisLock() {
        String key = UUID.randomUUID().toString();
        try {
            redisCacheTemplate.lock(key, Duration.ofMillis(500), () -> logger.info("### Redis lock runnable. ###"));
        } catch (BusinessException e) {
            Assertions.assertEquals(ApiErrorCode.SERVICE_BUSY.getName(), e.getCode().getName());
        }
    }

    @RepeatedTest(5)
    public void redisAopCache() {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        // Cache
        Example cache = cacheService.cache();
        Assertions.assertEquals(example.getName(), cache.getName());
        Assertions.assertEquals(example.getAge(), cache.getAge());
        Assertions.assertEquals(example.getPercent(), cache.getPercent());
        // Cache
        GenericExample<Example> genericExample = cacheService.genericCache();
        Assertions.assertEquals(example.getName(), genericExample.getName());
        Assertions.assertEquals(example.getAge(), genericExample.getData().getAge());
        Assertions.assertEquals(example.getPercent(), genericExample.getData().getPercent());
        // Cache SpEL
        Example cacheSpel = cacheService.cacheSpel(1L);
        Assertions.assertEquals(example.getName(), cacheSpel.getName());
        Assertions.assertEquals(example.getAge(), cacheSpel.getAge());
        Assertions.assertEquals(example.getPercent(), cacheSpel.getPercent());
    }

    @RepeatedTest(5)
    public void redisBreakdownCache() {
        // Cache Breakdown
        Example cache = cacheService.cacheNullValue();
        Assertions.assertNull(cache);
    }

    @RepeatedTest(2)
    public void redisAopDelete() {
        // Delete
        cacheService.delete();
        // Delete SpEL
        cacheService.deleteSpel(1L);
    }

    @RepeatedTest(10)
    public void redisAopLock() {

        applicationTaskExecutor.execute(() -> {
            try {
                // Lock
                cacheService.lock();
                // Lock SpEL
                cacheService.lockSpel(1L);
            } catch (BusinessException e) {
                logger.error(e.getMessage());
            }
        });
    }
}
