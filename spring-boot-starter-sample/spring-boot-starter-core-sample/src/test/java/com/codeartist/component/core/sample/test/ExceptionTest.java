package com.codeartist.component.core.sample.test;

import com.codeartist.component.core.entity.enums.ApiErrorCode;
import com.codeartist.component.core.entity.enums.ApiErrorCode.ApiHttpStatus;
import com.codeartist.component.core.support.test.AbstractSpringRunnerTests;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 接口异常测试
 *
 * @author J.N.AI
 * @date 2023/6/25
 */
public class ExceptionTest extends AbstractSpringRunnerTests {

    @Test
    void client() throws Exception {
        mockMvc.perform(get("/api/exception/client"))
                .andExpect(status().is(ApiHttpStatus.CLIENT_WARNING.getValue()))
                .andExpect(jsonPath("$.code").value(ApiHttpStatus.CLIENT_WARNING.getValue()))
                .andDo(print());
    }

    @Test
    void business() throws Exception {
        mockMvc.perform(get("/api/exception/business"))
                .andExpect(status().is(ApiHttpStatus.BUSINESS_WARNING.getValue()))
                .andExpect(jsonPath("$.code").value(ApiHttpStatus.BUSINESS_WARNING.getValue()))
                .andDo(print());
    }

    @Test
    void server() throws Exception {
        mockMvc.perform(get("/api/exception/server"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(ApiErrorCode.SERVICE_ERROR.getCode()))
                .andDo(print());
    }
}
