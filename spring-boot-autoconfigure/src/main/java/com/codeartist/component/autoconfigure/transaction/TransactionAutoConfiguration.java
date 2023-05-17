package com.codeartist.component.autoconfigure.transaction;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 事务配置
 *
 * @author 艾江南
 * @date 2022/7/22
 */
@ConditionalOnClass(PlatformTransactionManager.class)
@SpringBootConfiguration
public class TransactionAutoConfiguration {

    /**
     * 全局编程式事务配置
     */
    @Bean
    @ConditionalOnBean(PlatformTransactionManager.class)
    public TransactionTemplate transactionTemplate(PlatformTransactionManager txManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
        transactionTemplate.setTimeout(30);
        return transactionTemplate;
    }
}
