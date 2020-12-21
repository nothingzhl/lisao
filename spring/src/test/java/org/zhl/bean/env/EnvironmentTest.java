package org.zhl.bean.env;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(EnvConfig.class)
class EnvironmentTest {

    @Autowired
    private Environment environment;

    @Autowired
    ConfigurableListableBeanFactory factory;

    @Test
    void envTest() {
        Assertions.assertNotNull(environment);
    }

    @Test
    void beanDefinitionTest() {
        BeanDefinition beanDefinition = factory.getBeanDefinition("beanDefinitionClass");
        System.out.println(beanDefinition);
    }
}