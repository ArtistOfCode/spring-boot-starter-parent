package com.codeartist.component.mq.core.impl;

import com.codeartist.component.core.support.mq.bean.MqConsumerEvent;
import com.codeartist.component.core.support.mq.bean.MqContext;
import com.codeartist.component.core.support.mq.bean.MqType;
import com.codeartist.component.core.util.SpringContext;
import com.codeartist.component.mq.bean.MqProperties;
import com.codeartist.component.mq.core.MqConsumer;
import com.codeartist.component.mq.exception.MqException;
import com.codeartist.component.mq.metric.MqMetrics;
import com.codeartist.component.mq.support.MqEventListenerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * MQ消费者抽象
 *
 * @author 艾江南
 * @date 2021/5/19
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public abstract class AbstractMqConsumer implements MqConsumer {

    private final MqType type;

    @Value("${spring.application.name}")
    protected String applicationName;

    @Autowired
    private MqMetrics mqMetrics;
    @Autowired
    private MqEventListenerFactory factory;
    @Autowired
    protected MqProperties properties;
    @Autowired
    protected ThreadPoolTaskExecutor mqConsumerExecutor;

    protected abstract void doStart();

    protected abstract void doStop();

    protected abstract void doRegister(MqContext listener);

    @Override
    public void start() {
        factory.getListeners(type).forEach(this::doRegister);
        doStart();
        log.info("{} MQ container started.", type);
    }

    @Override
    public void stop() {
        doStop();
        log.info("{} MQ container stopped.", type);
    }

    @Override
    public void doPublish(MqContext message) {
        try {
            MqConsumerEvent event = new MqConsumerEvent(this, message);
            SpringContext.publishEvent(event);
            mqMetrics.consumer(message.getType(), message.getGroup(), message.getTopic(), event.getTimestamp());
        } catch (Exception e) {
            mqMetrics.consumerError(message.getType(), message.getGroup(), message.getTopic());
            throw new MqException(message, "MQ consumer error.", e);
        }
    }
}
