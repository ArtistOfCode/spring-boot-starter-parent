package com.codeartist.component.core.support.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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
        simpleMapper = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
        genericMapper = Jackson2ObjectMapperBuilder.json()
                .failOnUnknownProperties(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
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
