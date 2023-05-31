package com.codeartist.component.core.support.mq.annotatioin;

import com.codeartist.component.core.support.mq.bean.MqType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * RedisMQ消费者监听器注解
 *
 * @author 艾江南
 * @date 2021/5/8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@MqConsumerListener(type = MqType.REDIS)
public @interface RedisMqConsumer {

    @AliasFor(annotation = MqConsumerListener.class, attribute = "value")
    String value() default "";

    @AliasFor(annotation = MqConsumerListener.class, attribute = "topic")
    String topic() default "";
}
