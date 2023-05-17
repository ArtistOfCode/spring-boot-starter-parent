package com.codeartist.component.sample.controller;

import com.codeartist.component.mq.core.impl.MqProducers;
import com.codeartist.component.sample.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MQ测试接口
 *
 * @author 艾江南
 * @date 2022/11/25
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/mq")
public class MqController {

    @PostMapping
    public void send() {
        long id = RandomUtils.nextLong();
        User user = new User();
        user.setId(id);
        user.setName("艾江南");
        MqProducers.redis().send("TEST_TOPIC", user);
    }
}
