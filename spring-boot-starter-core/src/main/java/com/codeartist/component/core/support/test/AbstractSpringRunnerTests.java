package com.codeartist.component.core.support.test;

import org.mockito.BDDMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import java.nio.charset.StandardCharsets;

/**
 * SpringBoot单元测试
 *
 * @author 艾江南
 * @date 2020/7/15
 */
@ActiveProfiles({"junit", "local"})
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractSpringRunnerTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MockMvc mockMvc;

    // mock

    protected <T> void mock(T t, T ret) {
        BDDMockito.given(t).willReturn(ret);
    }

    // print

    protected ResultHandler print() {
        return result -> logger.info(new String(result.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8));
    }
}
