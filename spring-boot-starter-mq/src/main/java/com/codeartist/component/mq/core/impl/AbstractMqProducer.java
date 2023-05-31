package com.codeartist.component.mq.core.impl;

import com.codeartist.component.core.util.Assert;
import com.codeartist.component.core.util.JSON;
import com.codeartist.component.core.support.mq.bean.MqMessage;
import com.codeartist.component.core.support.mq.bean.MqType;
import com.codeartist.component.mq.core.MqProducer;
import com.codeartist.component.mq.metric.MqMetrics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MQ生产者抽象
 *
 * @author 艾江南
 * @date 2021/5/19
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractMqProducer implements MqProducer {

    private final MqType type;

    @Autowired
    private MqMetrics mqMetrics;

    protected abstract void doSend(MqMessage message);

    @Override
    public void send(MqMessage message) {
        String topic = message.getTopic();

        Assert.notBlank(topic, this.type + " MQ topic is blank");

        doSend(message);

        mqMetrics.producer(type, topic);
    }

    @Override
    public void init() {
        log.info("{} MQ producer init.", type);
        start();
    }

    @Override
    public void start() {
        log.info("{} MQ producer started.", type);
    }

    @Override
    public void stop() {
        log.info("{} MQ producer stopped.", type);
    }

    protected <T> String serialize(T data) {
        return JSON.toJSONString(data);
    }
}
