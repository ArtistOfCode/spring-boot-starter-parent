package com.codeartist.component.mq.core.impl;

import com.codeartist.component.mq.bean.MqHeaders;
import com.codeartist.component.mq.bean.MqMessage;
import com.codeartist.component.mq.bean.MqType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;

/**
 * MQ生产者工具
 *
 * @author 艾江南
 * @date 2021/7/22
 */
public final class MqProducers implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher applicationEventPublisher;

    public static MqSender redis() {
        return new MqSender(MqType.REDIS);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        MqProducers.applicationEventPublisher = applicationEventPublisher;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MqSender {

        private final MqType type;

        public <T> void send(String topic, T data) {
            send(null, topic, null, data);
        }

        public <T> void send(String topic, String tag, T data) {
            send(null, topic, tag, data);
        }

        public <T> void send(MqHeaders headers, String topic, T data) {
            send(headers, topic, null, data);
        }

        public <T> void send(MqHeaders headers, String topic, String tag, T data) {
            MqMessage message = MqMessage.builder()
                    .type(type)
                    .headers(headers)
                    .topic(topic)
                    .tag(tag)
                    .body(data)
                    .build();
            applicationEventPublisher.publishEvent(new PayloadApplicationEvent<>(this, message));
        }
    }
}
