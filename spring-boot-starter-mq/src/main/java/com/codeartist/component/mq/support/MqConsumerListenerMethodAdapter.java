package com.codeartist.component.mq.support;

import com.codeartist.component.core.support.mq.bean.MqConsumerEvent;
import com.codeartist.component.core.support.mq.bean.MqContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationListenerMethodAdapter;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;

/**
 * MQ消费者事件监听器适配器
 *
 * @author 艾江南
 * @date 2021/7/21
 */
public class MqConsumerListenerMethodAdapter extends ApplicationListenerMethodAdapter {

    private final MqContext annotation;

    public MqConsumerListenerMethodAdapter(String beanName, Class<?> targetClass, Method method, MqContext annotation) {
        super(beanName, targetClass, method);
        this.annotation = annotation;
    }

    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        Class<?> rawClass = eventType.getRawClass();
        if (rawClass == null) {
            return false;
        }
        return MqConsumerEvent.class.isAssignableFrom(rawClass);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        MqContext mqContext = ((MqConsumerEvent) event).getMqContext();
        if (shouldHandle(mqContext)) {
            Object result = doInvoke(mqContext);
            if (result != null) {
                handleResult(result);
            }
        }
    }

    /**
     * 监听器执行条件
     */
    private <T> boolean shouldHandle(MqContext mqContext) {
        return annotation.getType().equals(mqContext.getType())
                && annotation.getTopic().equals(mqContext.getTopic())
                && annotation.getTag().equals(mqContext.getTag());
    }
}
