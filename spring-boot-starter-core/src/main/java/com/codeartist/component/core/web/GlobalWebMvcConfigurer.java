package com.codeartist.component.core.web;

import com.codeartist.component.core.web.formatter.Base64FileFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 全局WebMvc配置
 *
 * @author 艾江南
 * @date 2021/5/25
 */
@RequiredArgsConstructor
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {

    private final ObjectProvider<HandlerInterceptorAdapter> interceptorAdapters;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Base64FileFormatter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptorAdapters.forEach(adapter -> registry.addInterceptor(adapter).addPathPatterns("/api/**"));
    }
}
