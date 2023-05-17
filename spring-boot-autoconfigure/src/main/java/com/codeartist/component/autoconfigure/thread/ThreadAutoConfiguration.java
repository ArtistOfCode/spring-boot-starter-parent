package com.codeartist.component.autoconfigure.thread;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * 线程配置，如果要定义自己的线程池另外创建Bean
 *
 * @author 艾江南
 * @date 2020/9/9
 */
@EnableAsync
@EnableScheduling
@SpringBootConfiguration
public class ThreadAutoConfiguration {

    @Bean
    public TaskExecutorCustomizer taskExecutorCustomizer() {
        return executor -> {
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(1000);
            executor.setKeepAliveSeconds(300);
            executor.setThreadNamePrefix("async-task-");
            executor.setRejectedExecutionHandler(new CallerRunsPolicy());
        };
    }
}
