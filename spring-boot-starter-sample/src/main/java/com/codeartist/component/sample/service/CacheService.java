package com.codeartist.component.sample.service;

import com.codeartist.component.core.support.cache.annotation.Cache;
import com.codeartist.component.core.support.cache.annotation.CacheDelete;
import com.codeartist.component.core.support.cache.annotation.CacheLock;
import com.codeartist.component.sample.entity.GenericExample;
import com.codeartist.component.sample.entity.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author 艾江南
 * @date 2022/8/18
 */
@Slf4j
@Service
public class CacheService {

    @Cache(key = "TEST_CACHE", timeout = 3, timeUnit = TimeUnit.MINUTES)
    public Example cache() {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        log.info("Enter method cache");
        return example;
    }

    @Cache(key = "GENERIC_TEST_CACHE", timeout = 3, timeUnit = TimeUnit.MINUTES)
    public GenericExample<Example> genericCache() {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        GenericExample<Example> genericExample = new GenericExample<>();
        genericExample.setName(example.getName());
        genericExample.setData(example);
        log.info("Enter method cache");
        return genericExample;
    }

    @Cache(key = "TEST_CACHE_SPEL", spel = "#id", timeout = 3, timeUnit = TimeUnit.MINUTES)
    public Example cacheSpel(Long id) {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        log.info("Enter method cache spel");
        return example;
    }

    @Cache(key = "g", timeout = 3, timeUnit = TimeUnit.MINUTES)
    public Example cacheNullValue() {
        log.info("Enter method null");
        return null;
    }

    @CacheDelete(key = "TEST_CACHE_NULL")
    public void delete() {
        log.info("Enter method delete");
    }

    @CacheDelete(key = "TEST_CACHE_SPEL_NULL", spel = "#id")
    public void deleteSpel(Long id) {
        log.info("Enter method spel delete");
    }

    @CacheLock(key = "TEST_CACHE_LOCK", timeout = 10, timeUnit = TimeUnit.SECONDS)
    public void lock() {
        log.info("Enter method lock");
    }

    @CacheLock(key = "TEST_CACHE_LOCK_SPEL", spel = "#id", timeout = 10, timeUnit = TimeUnit.SECONDS)
    public void lockSpel(Long id) {
        log.info("Enter method spel lock");
    }
}
