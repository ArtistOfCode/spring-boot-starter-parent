package com.codeartist.component.mq.support;

import com.codeartist.component.core.support.mq.annotatioin.MqConsumerListener;
import com.codeartist.component.core.support.mq.bean.MqContext;
import com.codeartist.component.core.support.mq.bean.MqType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MQ事件监听工厂
 *
 * @author 艾江南
 * @date 2021/7/20
 */
@Setter
@RequiredArgsConstructor
public class MqEventListenerFactory implements EventListenerFactory, EnvironmentAware, Ordered {

    private Environment environment;
    private Map<MqType, Set<MqContext<Void>>> listenerMap = new ConcurrentHashMap<>();

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE - 1;
    }

    @Override
    public boolean supportsMethod(Method method) {
        return AnnotatedElementUtils.hasAnnotation(method, MqConsumerListener.class);
    }

    @Override
    public ApplicationListener<?> createApplicationListener(String beanName, Class<?> type, Method method) {
        MqConsumerListener ann = AnnotatedElementUtils.findMergedAnnotation(method, MqConsumerListener.class);
        if (ann == null) {
            throw new IllegalStateException("No MqConsumerListener ann found on method: " + method);
        }
        MqContext<Void> annotation = parseMessage(method, ann);
        return new MqConsumerListenerMethodAdapter(beanName, type, method, annotation);
    }

    public Set<MqContext<Void>> getListeners(MqType type) {
        return this.listenerMap.get(type);
    }

    private MqContext<Void> parseMessage(Method method, MqConsumerListener ann) {
        String app = environment.getProperty("spring.application.name");
        ResolvableType resolvableType = ResolvableType.forMethodParameter(method, 0);
        Type bodyType = resolvableType.getGeneric().getType();

        MqContext<Void> annotation = MqContext.<Void>builder()
                .type(ann.type())
                .group(app + "-group")
                .topic(ann.topic())
                .tag(ann.tag())
                .bodyType(bodyType)
                .build();

        listenerMap.computeIfAbsent(ann.type(), k -> new HashSet<>());
        listenerMap.get(ann.type()).add(annotation);
        return annotation;
    }
}
