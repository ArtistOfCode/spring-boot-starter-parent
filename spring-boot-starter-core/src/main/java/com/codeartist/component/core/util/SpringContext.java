package com.codeartist.component.core.util;

import com.codeartist.component.core.exception.BadRequestException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;

/**
 * Spring上下文工具类
 *
 * @author J.N.AI
 * @date 2023/5/11
 */
public final class SpringContext implements EnvironmentAware, ApplicationContextAware {

    private static Environment environment;
    private static ApplicationContext applicationContext;
    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.byDefaultProvider().configure().buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    public static boolean acceptsProfiles(String... profiles) {
        return environment.acceptsProfiles(Profiles.of(profiles));
    }

    // Environment

    public static String resolvePlaceholders(String text) {
        return environment.resolvePlaceholders(text);
    }

    // ApplicationContext

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

    public static <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        return applicationContext.getBeanProvider(requiredType);
    }

    public static <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        return applicationContext.getBeanProvider(requiredType);
    }

    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    public static void publishEvent(Object event) {
        applicationContext.publishEvent(event);
    }

    // Validator

    public static <T> void validate(T object, Class<?>... groups) {
        handlerValidate(validator.validate(object, groups));
    }

    public static <T> void validateProperty(T object, String propertyName, Class<?>... groups) {
        handlerValidate(validator.validateProperty(object, propertyName, groups));
    }

    public static <T> void validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        handlerValidate(validator.validateValue(beanType, propertyName, value, groups));
    }

    private static <T> void handlerValidate(Set<ConstraintViolation<T>> violations) {
        if (CollectionUtils.isEmpty(violations)) {
            return;
        }
        ConstraintViolation<T> violation = violations.iterator().next();
        String field = violation.getPropertyPath().toString();
        throw new BadRequestException(field + violation.getMessage());
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
