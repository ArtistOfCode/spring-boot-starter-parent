package com.codeartist.component.sample.test.controller;

import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import com.codeartist.component.sample.entity.param.UserParam;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 艾江南
 * @date 2022/7/22
 */
public class ExceptionControllerTest extends AbstractSpringRunnerTests {

    @Test
    public void client() throws Exception {
        mockMvc.perform(get("/api/exception/client"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("请求异常"))
                .andDo(print());
    }

    @Test
    public void business() throws Exception {
        mockMvc.perform(get("/api/exception/business"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.code").value(HttpStatus.SERVICE_UNAVAILABLE.value()))
                .andExpect(jsonPath("$.message").value("业务异常"))
                .andDo(print());
    }

    @Test
    public void server() throws Exception {
        mockMvc.perform(get("/api/exception/server"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.message").value("服务器开小差"))
                .andDo(print());
    }

    @Test
    void args() throws Exception {
        mockMvc.perform(post("/api/exception/args"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("args.id: must not be null"));

        mockMvc.perform(post("/api/exception/body-args", new UserParam()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("name must not be blank"));
    }
}