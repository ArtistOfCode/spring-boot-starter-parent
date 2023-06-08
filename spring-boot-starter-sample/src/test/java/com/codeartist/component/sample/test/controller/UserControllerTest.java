package com.codeartist.component.sample.test.controller;

import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import com.codeartist.component.core.util.JSON;
import com.codeartist.component.sample.entity.param.UserParam;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 艾江南
 * @date 2022/11/24
 */
class UserControllerTest extends AbstractSpringRunnerTests {

    @Test
    void save() throws Exception {
        UserParam param = new UserParam();
        param.setName("艾江南");
        param.setUsername("AiJiangnan");
        param.setPassword("123456");

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(param))
                )
                .andExpect(status().isOk());

        param.setId(5L);
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(param))
                )
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/user/5"))
                .andExpect(status().isOk());
    }

    @Test
    void page() throws Exception {
        mockMvc.perform(get("/api/user/page")
                        .param("pageNo", "1")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current").value(1))
                .andDo(print());
    }
}