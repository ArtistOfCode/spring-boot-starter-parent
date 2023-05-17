package com.codeartist.component.cache.support.redis;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.metric.CacheMetrics;
import com.codeartist.component.cache.support.AbstractCacheTemplate;
import com.codeartist.component.core.support.serializer.TypeRef;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * 缓存抽象类
 *
 * @author 艾江南
 * @date 2021/5/25
 */
public abstract class AbstractRedisCacheTemplate extends AbstractCacheTemplate implements RedisCacheTemplate {

    public AbstractRedisCacheTemplate(CacheProperties cacheProperties, CacheMetrics cacheMetrics) {
        super(cacheProperties, cacheMetrics);
    }

    protected abstract String doGet(String key, String hashKey);

    protected abstract void doSet(String key, String hashKey, String data, Duration duration);

    // Hash

    @Override
    public <T> T get(String key, String hashKey, Class<T> clazz) {
        checkKey(key);
        checkKey(hashKey);
        String data = doGet(key, hashKey);
        if (data == null) {
            cacheMetrics.miss(key);
            return null;
        }
        cacheMetrics.hit(key);
        return deserialize(data, clazz);
    }

    @Override
    public <T> T get(String key, String hashKey, TypeRef<T> clazz) {
        checkKey(key);
        checkKey(hashKey);
        String data = doGet(key, hashKey);
        if (data == null) {
            cacheMetrics.miss(key);
            return null;
        }
        cacheMetrics.hit(key);
        return deserialize(data, clazz);
    }

    @Override
    public void set(String key, String hashKey, Duration duration, Object data) {
        checkKey(key);
        checkKey(hashKey);
        doSet(key, hashKey, serialize(data), duration);
    }

    @Override
    public <T> T cache(String key, Duration duration, TypeRef<T> clazz, Supplier<T> supplier) {
        String data = doGet(key);
        if (data != null) {
            cacheMetrics.hit(key);
            return deserialize(data, clazz);
        }
        T t = supplier.get();
        if (t == null) {
            doSet(key, NULL, Duration.ofSeconds(cacheProperties.getNullTimeout()));
        } else {
            doSet(key, serialize(t));
        }
        cacheMetrics.miss(key);
        return t;
    }
}
