package com.codeartist.component.cache.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置
 *
 * @author 艾江南
 * @date 2021/5/24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cache")
public class CacheProperties {

    public static final String DELIMITER = ":";

    /**
     * null存储过期时间
     */
    private Integer nullTimeout = 2 * 60;

    private Redis redis = new Redis();

    @Getter
    @Setter
    public static class Redis {
        private boolean enabled = true;
    }
}
