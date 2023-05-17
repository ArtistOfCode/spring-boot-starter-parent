package com.codeartist.component.autoconfigure.web;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * 公共配置注入
 *
 * @author 艾江南
 * @date 2022/12/2
 */
@Order(1)
public class CommonPropertySourceLocator implements PropertySourceLocator {

    private static final String BOOTSTRAP_COMMON_YAML = "bootstrap-common.yaml";

    @Override
    public PropertySource<?> locate(Environment environment) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        ClassPathResource resource = new ClassPathResource(BOOTSTRAP_COMMON_YAML);
        yaml.setResources(resource);
        Properties properties = yaml.getObject();
        if (properties == null) {
            properties = new Properties();
        }
        return new PropertiesPropertySource("commonProperties", properties);
    }
}
