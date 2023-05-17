package com.codeartist.component.autoconfigure.sleuth;

import brave.sampler.Sampler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.sampler.ProbabilityBasedSampler;
import org.springframework.cloud.sleuth.sampler.SamplerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 链路追踪组件
 *
 * @author 艾江南
 * @date 2021/9/7
 */
@Configuration
@ConditionalOnProperty(value = {"spring.sleuth.enabled"}, matchIfMissing = true)
public class TraceAutoConfiguration {

    @Bean
    public Sampler defaultTraceSampler(SamplerProperties config) {
        if (config.getProbability() != null) {
            return new ProbabilityBasedSampler(config);
        }
        return Sampler.ALWAYS_SAMPLE;
    }
}
