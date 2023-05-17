package com.codeartist.component.core.support.cache.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 缓存注解，该注解用于方法级缓存，缓存流程：
 * <ol>
 * <li>获取缓存</li>
 * <li>如果缓存存在，直接返回</li>
 * <li>缓存不存在，执行方法，将方法返回结果缓存后返回</li>
 * </ol>
 * <p>缓存使用 fastJSON 序列化，该注解支持动态键和过期时间，缓存键由 {@code value} 前缀和 {@code spelKey} 的SpEL表达式组成，
 * SpEL表达式的根是方法参数数组。
 * </p>
 * <p>
 * 过期时间默认为不过期，由 {@code timeout} 时间值和 {@code timeUnit} 时间单位组成。
 * </p>
 *
 * @author 艾江南
 * @since 2018-11-07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

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
    long timeout() default -1;

    /**
     * 缓存过期时间单位（默认：毫秒）
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
