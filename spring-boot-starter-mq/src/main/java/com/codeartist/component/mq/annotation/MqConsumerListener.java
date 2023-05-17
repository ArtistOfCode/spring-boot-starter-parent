package com.codeartist.component.mq.annotation;

import com.codeartist.component.mq.bean.MqHeaders;
import com.codeartist.component.mq.bean.MqType;
import com.codeartist.component.mq.support.MqConsumerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * MQ消费者监听器注解
 *
 * @author 艾江南
 * @date 2021/5/8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EventListener(classes = MqConsumerEvent.class)
public @interface MqConsumerListener {

    @AliasFor("topic")
    String value() default "";

    MqType type();

    @AliasFor("value")
    String topic() default "";

    String tag() default MqHeaders.DEFAULT_TAG;
}
