package com.codeartist.component.core.support.cache.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁，该注解用于方法级缓存，缓存流程：
 * <ol>
 * <li>缓存当前时间戳</li>
 * <li>缓存成功：不存在锁，执行方法后清除锁</li>
 * <li>缓存失败：存在锁，抛出业务异常</li>
 * </ol>
 * <p>该注解支持动态键和过期时间，Redis键由 {@code value} 前缀和 {@code spelKey} 的SpEL表达式组成，
 * SpEL表达式的根是方法参数数组。
 * </p>
 * <p>
 * 过期时间由 {@code timeout} 时间值和 {@code timeUnit} 时间单位组成。
 * </p>
 *
 * @author 艾江南
 * @date 2019/5/6
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    /**
     * 缓的Key
     */
    String key();

    /**
     * 缓的Key（支持SpEL表达式）
     */
    String spel() default "";

    /**
     * 缓存过期时间
     */
    long timeout();

    /**
     * 缓存过期时间单位
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
