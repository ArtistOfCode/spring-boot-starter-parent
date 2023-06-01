package com.codeartist.component.sample.test.util;

import com.codeartist.component.core.util.JSON;
import com.codeartist.component.sample.entity.GenericExample;
import com.codeartist.component.sample.entity.Example;
import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * JSON
 *
 * @author 艾江南
 * @date 2023/4/27
 */
public class JacksonTest extends AbstractSpringRunnerTests {

    @Autowired
    @Qualifier("mappingJackson2HttpMessageConverter")
    private HttpMessageConverter<Object> httpMessageConverter;

    @Test
    void json() throws IOException {
        Example example = new Example();
        example.setName("艾江南");
        example.setAge(27L);
        example.setPercent(new BigDecimal("100.8"));
        GenericExample<Example> genericExample = new GenericExample<>();
        genericExample.setName(example.getName());
        genericExample.setData(example);

        String jsonString = JSON.toJSONString(genericExample);

        System.out.println(jsonString);
        Object read = httpMessageConverter.read(GenericExample.class, new MappingJacksonInputMessage(
                new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8)),
                new HttpHeaders()
        ));

        System.out.println(read);
    }
}
