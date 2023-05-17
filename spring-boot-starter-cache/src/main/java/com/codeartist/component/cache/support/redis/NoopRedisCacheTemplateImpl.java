package com.codeartist.component.cache.support.redis;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.metric.CacheMetrics;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * 没有任何操作的Redis缓存实现
 * <p>
 * 可以在本地调试的时候禁用Redis缓存，不用Redis服务器支持，达到离线开发的目的
 *
 * @author 艾江南
 * @date 2021/5/24
 */
public class NoopRedisCacheTemplateImpl extends AbstractRedisCacheTemplate implements RedisCacheTemplate {

    public NoopRedisCacheTemplateImpl(CacheProperties cacheProperties, CacheMetrics cacheMetrics) {
        super(cacheProperties, cacheMetrics);
    }

    @Override
    protected String doGet(String key) {
        return null;
    }

    @Override
    protected String doGet(String key, String hashKey) {
        return null;
    }

    @Override
    protected void doSet(String key, String data) {
    }

    @Override
    protected void doSet(String key, String data, Duration duration) {
    }

    @Override
    protected void doSet(String key, String hashKey, String data, Duration duration) {
    }

    @Override
    public void delete(String key) {
    }

    @Override
    public void delete(String key, String... hashKey) {
    }

    @Override
    public <R> R lock(String key, Duration duration, Supplier<R> supplier) {
        return supplier.get();
    }

    @Override
    public void expire(String key, Duration duration) {
    }

    @Override
    public boolean exist(String key) {
        return false;
    }

    @Override
    public long inc(String key) {
        return 0L;
    }
}
