package com.codeartist.component.cache;

import com.codeartist.component.core.support.serializer.TypeRef;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Redis缓存接口
 *
 * @author 艾江南
 * @date 2022/4/25
 */
public interface RedisCacheTemplate extends CacheTemplate {

    <T> T cache(String key, Duration duration, TypeRef<T> clazz, Supplier<T> supplier);

    /*****Hash缓存*****/

    <T> T get(String key, String hashKey, Class<T> clazz);

    <T> T get(String key, String hashKey, TypeRef<T> clazz);

    void set(String key, String hashKey, Duration duration, Object data);

    void delete(String key, String... hashKey);

    /*****分布式锁*****/

    <T> T lock(String key, Duration duration, Supplier<T> supplier);

    default void lock(String key, Duration duration, Runnable runnable) {
        lock(key, duration, () -> {
            runnable.run();
            return null;
        });
    }

    /*****其他操作*****/

    void expire(String key, Duration duration);

    long inc(String key);
}
