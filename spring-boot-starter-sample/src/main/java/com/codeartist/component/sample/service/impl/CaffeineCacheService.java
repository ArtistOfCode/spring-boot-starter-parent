package com.codeartist.component.sample.service.impl;

import com.codeartist.component.core.support.cache.annotation.LocalCache;
import com.codeartist.component.core.support.cache.annotation.LocalCacheDelete;
import com.codeartist.component.sample.entity.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 艾江南
 * @date 2022/8/18
 */
@Slf4j
@Service
public class CaffeineCacheService {

    @LocalCache(key = "TEST_CACHE")
    public Example cache() {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        log.info("Enter method cache");
        return example;
    }

    @LocalCache(key = "TEST_CACHE_SPEL", spel = "#id")
    public Example cacheSpel(Long id) {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        log.info("Enter method cache spel");
        return example;
    }

    @LocalCache(key = "TEST_CACHE_NULL")
    public Example cacheNullValue() {
        log.info("Enter method null");
        return null;
    }

    @LocalCacheDelete(key = "TEST_CACHE_NULL")
    public void delete() {
        log.info("Enter method delete");
    }

    @LocalCacheDelete(key = "TEST_CACHE_SPEL_NULL", spel = "#id")
    public void deleteSpel(Long id) {
        log.info("Enter method spel delete");
    }
}
