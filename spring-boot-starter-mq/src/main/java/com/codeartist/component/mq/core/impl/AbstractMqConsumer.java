package com.codeartist.component.mq.core.impl;

import com.codeartist.component.core.support.serializer.TypeRef;
import com.codeartist.component.core.util.JSON;
import com.codeartist.component.core.support.mq.bean.MqContext;
import com.codeartist.component.mq.bean.MqProperties;
import com.codeartist.component.core.support.mq.bean.MqType;
import com.codeartist.component.mq.core.MqConsumer;
import com.codeartist.component.mq.exception.MqException;
import com.codeartist.component.mq.metric.MqMetrics;
import com.codeartist.component.core.support.mq.bean.MqConsumerEvent;
import com.codeartist.component.mq.support.MqEventListenerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;

/**
 * MQ消费者抽象
 *
 * @author 艾江南
 * @date 2021/5/19
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public abstract class AbstractMqConsumer implements MqConsumer, ApplicationContextAware {

    private final MqType type;

    private ApplicationContext applicationContext;

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

    protected abstract void doRegister(MqContext<Void> listener);

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
    public <T> void doPublish(MqContext<T> message) {
        try {
            MqConsumerEvent<T> event = new MqConsumerEvent<>(message);
            applicationContext.publishEvent(event);
            mqMetrics.consumer(message.getType(), message.getGroup(), message.getTopic(), event.getTimestamp());
        } catch (Exception e) {
            mqMetrics.consumerError(message.getType(), message.getGroup(), message.getTopic());
            throw new MqException(message, "MQ consumer error.", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected String getApplicationName() {
        return applicationContext.getEnvironment().getProperty("spring.application.name");
    }

    protected <T> T deserialize(String data, Type type) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }

        return JSON.parseObject(data, new TypeRef<T>() {
            @Override
            public Type getType() {
                return type;
            }
        });
    }
}
