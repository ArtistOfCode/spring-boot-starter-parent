package com.codeartist.component.core.support.serializer;

import com.codeartist.component.core.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Supplier;

/**
 * JSON序列化
 *
 * @author 艾江南
 * @date 2022/8/18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JacksonSerializer {

    private static final String CLASS_PROPERTY_NAME = "@c";

    private static final ObjectMapper simpleMapper;
    private static final ObjectMapper genericMapper;

    static {
        Supplier<Jackson2ObjectMapperBuilder> supplier = () -> Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateUtil.LONG_DATE_FORMATTER))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateUtil.LONG_TIME_FORMATTER))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateUtil.LONG_DATETIME_FORMATTER));

        simpleMapper = supplier.get().build();
        genericMapper = supplier.get().failOnUnknownProperties(false).build();

        genericMapper.activateDefaultTypingAsProperty(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, CLASS_PROPERTY_NAME);
    }

    /**
     * 支持泛型的JSON序列化
     */
    public static ObjectMapper genericMapper() {
        return genericMapper;
    }

    /**
     * 简单的JSON序列化
     */
    public static ObjectMapper simpleMapper() {
        return simpleMapper;
    }
}
