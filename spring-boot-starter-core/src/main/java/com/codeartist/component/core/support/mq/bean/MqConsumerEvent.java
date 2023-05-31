package com.codeartist.component.core.support.mq.bean;

import com.codeartist.component.core.support.mq.bean.MqContext;
import com.codeartist.component.core.support.mq.bean.MqType;
import org.springframework.context.ApplicationEvent;

/**
 * MQ消费者事件对象
 *
 * @author 艾江南
 * @date 2021/7/21
 */
public class MqConsumerEvent<T> extends ApplicationEvent {

    private final MqType type;
    private final String topic;
    private final String tag;

    public MqConsumerEvent(MqContext<T> source) {
        super(source);
        this.type = source.getType();
        this.topic = source.getTopic();
        this.tag = source.getTag();
    }

    public MqType getType() {
        return this.type;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getTag() {
        return this.tag;
    }
}
