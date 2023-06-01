package com.codeartist.component.sample.test.controller;

import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 艾江南
 * @date 2023/2/21
 */
class SpringMvcControllerTest extends AbstractSpringRunnerTests {

    public static final String BASE64_FILE = "data:text/plain;base64,VnVl5Li76aKY77yaCuS4u+iJsu+8miM0MmI5ODMK5Ymv6Imy77yaI2U3ZWNmMwrpu5HoibLvvJojMzA0NDU1CuiTneiJsu+8miMzZTcxZmEK57Sr6Imy77yaIzg2NWJjMgrnuqLoibLvvJojZmY1ODVk";

    @Test
    public void base64() throws Exception {
        mockMvc.perform(post("/api/mvc/base64")
                        .param("file", BASE64_FILE))
                .andExpect(status().isOk())
                .andDo(print());
    }
}