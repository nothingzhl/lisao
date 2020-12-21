package org.zhl.bean.beandefinition;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class SelfBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AbstractBeanDefinition beanDefinition =
                BeanDefinitionBuilder.genericBeanDefinition(BeanDefinitionClass.class)
                        .addPropertyValue("name",
                                "zhanghanlin").getBeanDefinition();
        registry.registerBeanDefinition("testBeanDefinition",
                beanDefinition);

    }
}
