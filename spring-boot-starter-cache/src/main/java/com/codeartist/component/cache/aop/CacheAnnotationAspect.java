package com.codeartist.component.cache.aop;

import com.codeartist.component.cache.RedisCacheTemplate;
import com.codeartist.component.cache.bean.CacheProperties;
import com.codeartist.component.cache.exception.CacheException;
import com.codeartist.component.core.support.cache.annotation.Cache;
import com.codeartist.component.core.support.cache.annotation.CacheDelete;
import com.codeartist.component.core.support.cache.annotation.CacheLock;
import com.codeartist.component.core.support.serializer.TypeRef;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * 缓存自定义注解切片
 *
 * @author 艾江南
 * @since 2018-11-07
 */
@Aspect
@RequiredArgsConstructor
public class CacheAnnotationAspect {

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final RedisCacheTemplate redisCacheTemplate;

    /**
     * 缓存
     * <p>
     * NOTE：
     * <p>
     * 1. 从Redis获取方法返回值缓存
     * <p>
     * 2. 如果缓存存在，直接返回
     * <p>
     * 3. 缓存不存在，执行方法，将方法返回结果缓存后返回
     */
    @Around("@annotation(cache)")
    public Object doAround(ProceedingJoinPoint joinPoint, Cache cache) {

        String spelKey = getSpelKey(joinPoint, cache.key(), cache.spel());
        Duration duration = Duration.ofMillis(cache.timeUnit().toMillis(cache.timeout()));

        return redisCacheTemplate.cache(spelKey, duration, new TypeRef<Object>() {
            @Override
            public Type getType() {
                return ((MethodSignature) joinPoint.getSignature()).getMethod().getGenericReturnType();
            }
        }, exceptionWrapper(joinPoint));
    }

    /**
     * 清除缓存
     * NOTE：
     * 1. 方法执行完后清除缓存
     */
    @After("@annotation(evict)")
    public void doAfter(JoinPoint joinPoint, CacheDelete evict) {
        String spelKey = getSpelKey(joinPoint, evict.key(), evict.spel());
        redisCacheTemplate.delete(spelKey);
    }

    /**
     * 分布式锁
     * <p>
     * NOTE：
     * <p>
     * 1. 缓存当前时间戳
     * <p>
     * 2. 缓存成功：不存在锁，执行方法后清除锁
     * <p>
     * 3. 缓存失败：存在锁，抛出业务异常
     */
    @Around("@annotation(lock)")
    public Object doAround(ProceedingJoinPoint joinPoint, CacheLock lock) {
        String spelKey = getSpelKey(joinPoint, lock.key(), lock.spel());
        Duration duration = Duration.ofMillis(lock.timeUnit().toMillis(lock.timeout()));
        RedisCacheTemplate redisCacheTemplate = this.redisCacheTemplate;
        return redisCacheTemplate.lock(spelKey, duration, exceptionWrapper(joinPoint));
    }

    /**
     * 获取Spel键
     */
    private String getSpelKey(JoinPoint joinPoint, String key, String spelKey) {
        if (StringUtils.isEmpty(spelKey)) {
            return key;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodBasedEvaluationContext evaluationContext = new MethodBasedEvaluationContext(methodSignature.getMethod(),
                methodSignature.getMethod(), joinPoint.getArgs(), parameterNameDiscoverer);
        String value = parser.parseExpression(spelKey).getValue(evaluationContext, String.class);
        return StringUtils.isEmpty(value) ? key : key + CacheProperties.DELIMITER + value;
    }

    private Supplier<Object> exceptionWrapper(ProceedingJoinPoint joinPoint) {
        return () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throw new CacheException(e);
            }
        };
    }
}
