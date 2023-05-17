package com.codeartist.component.autoconfigure.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.codeartist.component.core.support.serializer.JacksonSerializer;
import com.codeartist.component.core.support.uuid.DefaultIdGenerator;
import com.codeartist.component.core.support.uuid.IdGenerator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * MyBatis配置
 *
 * @author 艾江南
 * @date 2022/9/2
 */
@SpringBootConfiguration
@ConditionalOnClass({MybatisPlusInterceptor.class, JacksonTypeHandler.class})
public class MyBatisConfiguration {

    @PostConstruct
    public void init() {
        // MyBatisPlus默认的JSON序列化配置
        JacksonTypeHandler.setObjectMapper(JacksonSerializer.simpleMapper());
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public IdGenerator idGenerator() {
        return new DefaultIdGenerator();
    }
}
