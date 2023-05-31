package com.codeartist.component.mq.core;

import com.codeartist.component.core.support.mq.bean.MqMessage;
import com.codeartist.component.core.support.mq.bean.MqType;

/**
 * MQ生产者
 *
 * @author 艾江南
 * @date 2021/5/8
 */
public interface MqProducer extends MqLifecycle {

    MqType getType();

    void send(MqMessage message);
}
