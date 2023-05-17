package com.codeartist.component.cache.support.redis;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.metric.CacheMetrics;
import com.codeartist.component.core.entity.enums.ApiErrorCode;
import com.codeartist.component.core.exception.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Redis缓存
 *
 * @author 艾江南
 * @date 2021/5/24
 */
public class RedisCacheTemplateImpl extends AbstractRedisCacheTemplate implements RedisCacheTemplate {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisCacheTemplateImpl(StringRedisTemplate stringRedisTemplate, CacheProperties cacheProperties,
                                  CacheMetrics cacheMetrics) {
        super(cacheProperties, cacheMetrics);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected String doGet(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    protected String doGet(String key, String hashKey) {
        return (String) stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    protected void doSet(String key, String data) {
        stringRedisTemplate.opsForValue().set(key, data);
    }

    @Override
    protected void doSet(String key, String data, Duration duration) {
        stringRedisTemplate.opsForValue().set(key, data, duration);
    }

    @Override
    protected void doSet(String key, String hashKey, String data, Duration duration) {
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            @SuppressWarnings("unchecked")
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.opsForHash().put((K) key, hashKey, data);
                operations.expire((K) key, duration.toMillis(), TimeUnit.MILLISECONDS);
                return null;
            }
        });
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void delete(String key, String... hashKey) {
        stringRedisTemplate.opsForHash().delete(key, (Object[]) hashKey);
    }

    /**
     * FIXME 后续使用Lua脚本代替分布式锁功能
     */
    @Override
    public <R> R lock(String key, Duration duration, Supplier<R> supplier) {
        checkKey(key);
        long data = System.currentTimeMillis();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(key, serialize(data), duration);
        boolean getLock = lock == null || lock;
        if (getLock) {
            try {
                return supplier.get();
            } finally {
                delete(key);
            }
        }
        long value = Optional.ofNullable(doGet(key)).map(Long::valueOf).orElse(0L);
        if (System.currentTimeMillis() - value > duration.toMillis()) {
            delete(key);
        }
        throw new BusinessException(ApiErrorCode.SERVICE_BUSY);
    }

    @Override
    public void expire(String key, Duration duration) {
        checkKey(key);
        stringRedisTemplate.expire(key, duration.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean exist(String key) {
        checkKey(key);
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }

    @Override
    public long inc(String key) {
        Long increment = stringRedisTemplate.opsForValue().increment(key);
        return increment == null ? 0L : increment;
    }
}
