package com.codeartist.component.mq.support;

import com.codeartist.component.core.support.mq.bean.MqMessage;
import com.codeartist.component.mq.core.MqProducer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;

import java.util.Objects;

/**
 * MQ生产者监听器
 *
 * @author J.N.AI
 * @date 2023/5/16
 */
public class MqProducerListener implements ApplicationListener<PayloadApplicationEvent<MqMessage>> {

    @Autowired
    private ObjectProvider<MqProducer> mqProducerProvider;

    @Override
    public void onApplicationEvent(PayloadApplicationEvent<MqMessage> event) {
        if (!mqProducerProvider.iterator().hasNext()) {
            return;
        }

        MqMessage message = event.getPayload();

        mqProducerProvider.stream()
                .filter(p -> Objects.equals(p.getType(), message.getType()))
                .forEach(p -> p.send(message));
    }
}
