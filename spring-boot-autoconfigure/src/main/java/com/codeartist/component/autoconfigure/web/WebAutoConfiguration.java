package com.codeartist.component.autoconfigure.web;

import com.codeartist.component.core.support.metric.Metrics;
import com.codeartist.component.core.util.SpringContext;
import com.codeartist.component.core.web.GlobalWebMvcConfigurer;
import com.codeartist.component.core.web.handler.ClientExceptionHandler;
import com.codeartist.component.core.web.handler.ServerExceptionHandler;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Web全局配置
 *
 * @author 艾江南
 * @date 2022/7/15
 */
@ConditionalOnClass(WebMvcConfigurer.class)
@SpringBootConfiguration
public class WebAutoConfiguration {

    @Bean
    public SpringContext springContext() {
        return new SpringContext();
    }

    @Bean
    public ServerExceptionHandler serverExceptionHandler(Metrics metrics) {
        return new ServerExceptionHandler(metrics);
    }

    @Bean
    public ClientExceptionHandler clientExceptionHandler() {
        return new ClientExceptionHandler();
    }

    @Bean
    public GlobalWebMvcConfigurer globalWebMvcConfigurer(ObjectProvider<HandlerInterceptorAdapter> interceptors) {
        return new GlobalWebMvcConfigurer(interceptors);
    }

    /**
     * Jackson序列化配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(JacksonProperties properties) {
        return builder -> {
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDateFormat());
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        };
    }
}
