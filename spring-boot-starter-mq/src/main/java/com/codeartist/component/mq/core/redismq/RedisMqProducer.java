package com.codeartist.component.mq.core.redismq;

import com.codeartist.component.core.support.mq.bean.MqHeaders;
import com.codeartist.component.core.support.mq.bean.MqMessage;
import com.codeartist.component.core.support.mq.bean.MqType;
import com.codeartist.component.mq.core.impl.AbstractMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * RedisMQ生产者
 *
 * @author 艾江南
 * @date 2021/5/8
 */
@Slf4j
public class RedisMqProducer extends AbstractMqProducer {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisMqProducer(StringRedisTemplate stringRedisTemplate) {
        super(MqType.REDIS);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doSend(MqMessage message) {

        Map<String, String> raw = new HashMap<>();

        String topic = message.getTopic();
        MqHeaders headers = message.getHeaders();
        Object body = message.getBody();

        if (!CollectionUtils.isEmpty(headers)) {
            raw.putAll(headers);
        }

        raw.put(MqHeaders.BODY_KEY, serialize(body));
        StringRecord record = StreamRecords.string(raw).withStreamKey(topic);
        RecordId id = stringRedisTemplate.opsForStream().add(record);

        log.info("Redis MQ send message topic:{}, recordId:{}", topic, id);
    }
}
