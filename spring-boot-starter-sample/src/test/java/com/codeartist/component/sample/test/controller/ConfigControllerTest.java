package com.codeartist.component.sample.test.controller;

import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 艾江南
 * @date 2022/11/8
 */
class ConfigControllerTest extends AbstractSpringRunnerTests {

    @Test
    void config() throws Exception {
        mockMvc.perform(get("/api/config"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}