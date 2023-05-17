package com.codeartist.component.core.support.cache.annotation;

import java.lang.annotation.*;

/**
 * 清除缓存注解，与 {@link LocalCache} 配置使用，该注解只生成动态键，键由 {@code value} 前缀和 {@code spelKey} 的SpEL表达式组成，
 * SpEL表达式的根是方法参数数组。
 *
 * @author 艾江南
 * @see LocalCache
 * @since 2018-11-07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocalCacheDelete {

    /**
     * 缓的Key
     */
    String key();

    /**
     * 缓的Key（支持SpEL表达式）
     */
    String spel() default "";
}
