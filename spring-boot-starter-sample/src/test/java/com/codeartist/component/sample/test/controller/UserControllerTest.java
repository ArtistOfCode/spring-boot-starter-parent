package com.codeartist.component.sample.test.controller;

import com.codeartist.component.sample.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 艾江南
 * @date 2022/11/24
 */
class UserControllerTest extends AbstractSpringRunnerTests {

    @Test
    void selectPage() throws Exception {
        mockMvc.perform(get("/api/user/page")
                        .param("pageNo", "1")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}