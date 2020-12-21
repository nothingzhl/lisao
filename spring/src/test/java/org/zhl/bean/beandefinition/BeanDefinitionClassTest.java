package org.zhl.bean.beandefinition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(BeanDefinitionConfig.class)
class BeanDefinitionClassTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testBeanDefinitionTest() {
        BeanDefinitionClass bean = context.getBean(BeanDefinitionClass.class);
        System.out.println(bean);
    }
}