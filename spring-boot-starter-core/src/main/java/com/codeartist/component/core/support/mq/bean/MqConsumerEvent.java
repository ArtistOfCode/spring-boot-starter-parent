package com.codeartist.component.core.support.mq.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * MQ消费者事件对象
 *
 * @author 艾江南
 * @date 2021/7/21
 */
@Getter
@Setter
@JsonIgnoreProperties({"source", "timestamp"})
public class MqConsumerEvent extends ApplicationEvent {

    private final MqContext mqContext;

    public MqConsumerEvent(Object source, MqContext mqContext) {
        super(source);
        this.mqContext = mqContext;
    }
}
