package com.codeartist.component.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.util.Map;

/**
 * Spring上下文工具类
 *
 * @author J.N.AI
 * @date 2023/5/11
 */
public final class SpringContext implements EnvironmentAware, ApplicationContextAware {

    private static Environment environment;
    private static ApplicationContext applicationContext;

    public static boolean acceptsProfiles(String... profiles) {
        return environment.acceptsProfiles(Profiles.of(profiles));
    }

    public static String resolvePlaceholders(String text) {
        return environment.resolvePlaceholders(text);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static Object getBean(String name, Object... args) {
        return applicationContext.getBean(name, args);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(Class<T> requiredType, Object... args) {
        return applicationContext.getBean(requiredType, args);
    }

    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    public static void publishEvent(Object event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringContext.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}
