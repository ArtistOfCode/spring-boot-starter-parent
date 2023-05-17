package com.codeartist.component.cache.support;

import com.codeartist.component.cache.CacheTemplate;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.exception.CacheException;
import com.codeartist.component.cache.metric.CacheMetrics;
import com.codeartist.component.core.support.serializer.TypeRef;
import com.codeartist.component.core.util.Assert;
import com.codeartist.component.core.util.JSON;

import java.time.Duration;

/**
 * 缓存抽象类
 *
 * @author 艾江南
 * @date 2021/5/25
 */
public abstract class AbstractCacheTemplate implements CacheTemplate {

    /**
     * null值缓存，防止缓存击穿，null值缓存时间默认为2分钟
     *
     * @see CacheProperties#getNullTimeout()
     */
    protected static final String NULL = "null";

    protected final CacheMetrics cacheMetrics;
    protected final CacheProperties cacheProperties;

    public AbstractCacheTemplate(CacheProperties cacheProperties, CacheMetrics cacheMetrics) {
        this.cacheProperties = cacheProperties;
        this.cacheMetrics = cacheMetrics;
    }

    protected abstract String doGet(String key);

    protected abstract void doSet(String key, String data);

    protected abstract void doSet(String key, String data, Duration duration);

    @Override
    public <T> T get(String key, Class<T> clazz) {
        checkKey(key);
        String data = doGet(key);
        if (data == null) {
            cacheMetrics.miss(key);
            return null;
        }
        cacheMetrics.hit(key);
        return deserialize(data, clazz);
    }

    @Override
    public <T> T get(String key, TypeRef<T> clazz) {
        checkKey(key);
        String data = doGet(key);
        if (data == null) {
            cacheMetrics.miss(key);
            return null;
        }
        cacheMetrics.hit(key);
        return deserialize(data, clazz);
    }

    @Override
    public void set(String key, Object data) {
        checkKey(key);
        if (data == null) {
            doSet(key, serialize(null), Duration.ofSeconds(cacheProperties.getNullTimeout()));
        } else {
            doSet(key, serialize(data));
        }
    }

    @Override
    public void set(String key, Object data, Duration duration) {
        checkKey(key);
        if (data == null) {
            doSet(key, serialize(null), Duration.ofSeconds(cacheProperties.getNullTimeout()));
        } else {
            doSet(key, serialize(data), duration);
        }
    }

    protected void checkKey(String key) {
        Assert.notBlank(key, () -> new CacheException("Cache key is blank."));
    }

    protected String serialize(Object data) {
        return JSON.toJSONString(data);
    }

    protected <T> T deserialize(String data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }

    protected <T> T deserialize(String data, TypeRef<T> clazz) {
        return JSON.parseObject(data, clazz);
    }
}
