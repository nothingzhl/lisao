package org.zhl.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 循环依赖相关
 *
 * @see org.springframework.beans.factory.support.DefaultSingletonBeanRegistry
 */
public class TheCycleDeTest {

    ApplicationContext context;


    @Test
    void constructorCycleTest() {

        // 构造器注入时会初始化bean的同时设置相关依赖，循环依赖会导致在 create bean pool 无法设置属性
        Assertions.assertThrows(BeanCreationException.class, () -> {
            context = new ClassPathXmlApplicationContext("classpath:spring-cycle-constructor.xml");
        });
    }

    @Test
    void setterCycleTest() {

        // setter 注入会先利用默认构造器实例化bean，然后设置相关对象属性
        context = new ClassPathXmlApplicationContext("classpath:spring-cycle-setter.xml");
        ClassA bean = context.getBean(ClassA.class);
        Assertions.assertNotNull(bean);
    }
}
