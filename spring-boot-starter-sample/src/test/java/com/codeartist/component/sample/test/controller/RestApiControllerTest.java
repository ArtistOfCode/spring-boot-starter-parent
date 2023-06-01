package com.codeartist.component.sample.test.controller;

import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 艾江南
 * @date 2022/7/22
 */
public class RestApiControllerTest extends AbstractSpringRunnerTests {

    @Test
    public void longToStr() throws Exception {
        mockMvc.perform(get("/api/rest/longToStr"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").isString())
                .andDo(print());
    }
}