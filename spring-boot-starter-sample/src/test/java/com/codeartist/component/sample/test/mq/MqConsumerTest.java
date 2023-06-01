package com.codeartist.component.sample.test.mq;

import com.codeartist.component.mq.core.impl.MqProducers;
import com.codeartist.component.sample.entity.User;
import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * MQ消费者测试
 *
 * @author 艾江南
 * @date 2022/11/25
 */
public class MqConsumerTest extends AbstractSpringRunnerTests {

    @Test
    void redisConsumer() throws InterruptedException {
        long id = RandomUtils.nextLong();
        User user = new User();
        user.setId(id);
        user.setName("艾江南");
        MqProducers.redis().send("TEST_TOPIC", user);
        TimeUnit.SECONDS.sleep(30);
    }
}
