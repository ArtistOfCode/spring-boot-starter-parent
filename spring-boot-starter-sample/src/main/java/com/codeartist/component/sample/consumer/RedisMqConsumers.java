package com.codeartist.component.sample.consumer;

import com.codeartist.component.core.util.JSON;
import com.codeartist.component.mq.annotation.RedisMqConsumer;
import com.codeartist.component.mq.bean.MqContext;
import com.codeartist.component.mq.bean.MqMessage;
import com.codeartist.component.mq.bean.MqType;
import com.codeartist.component.sample.entity.Example;
import com.codeartist.component.sample.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * RedisMQ消费者
 *
 * @author 艾江南
 * @date 2022/11/25
 */
@Slf4j
@Component
public class RedisMqConsumers {

    @RedisMqConsumer("TEST_TOPIC")
    public MqMessage consumer1(MqContext<User> message) {
        String group = message.getGroup();
        String topic = message.getTopic();
        String tag = message.getTag();
        User body = message.getBody();
        log.info("Consumer1 message at group:{}, topic:{}, tag:{}\n{}", group, topic, tag, JSON.toJSONString(body));

        Example example = new Example();
        example.setName("Example");
        example.setAge(28L);
        example.setPercent(BigDecimal.TEN);

        return MqMessage.builder()
                .type(MqType.REDIS)
                .topic("TEST_TOPIC_1")
                .body(example)
                .build();
    }

    @RedisMqConsumer("TEST_TOPIC_1")
    public void consumer2(MqContext<Example> message) {
        String group = message.getGroup();
        String topic = message.getTopic();
        String tag = message.getTag();
        Example body = message.getBody();
        log.info("Consumer2 message at group:{}, topic:{}, tag:{}\n{}", group, topic, tag, JSON.toJSONString(body));
    }
}
