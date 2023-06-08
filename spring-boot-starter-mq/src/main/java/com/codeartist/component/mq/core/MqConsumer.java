package com.codeartist.component.mq.core;

import com.codeartist.component.core.support.mq.bean.MqContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * MQ消费者
 *
 * @author 艾江南
 * @date 2021/5/8
 */
public interface MqConsumer extends MqLifecycle, ApplicationRunner {

    void doPublish(MqContext message);

    @Override
    default void run(ApplicationArguments args) {
        start();
    }
}
