package com.codeartist.component.autoconfigure.metrics;

import com.codeartist.component.core.support.metric.Metrics;
import com.codeartist.component.core.support.metric.NoopMetrics;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 指标组件
 *
 * @author 艾江南
 * @date 2022/7/15
 */
@SpringBootConfiguration
public class MetricAutoConfiguration {

    /**
     * 默认使用指标收集
     */
    @Bean
    @ConditionalOnMissingBean(Metrics.class)
    public Metrics defaultMetrics() {
        return new NoopMetrics();
    }
}
