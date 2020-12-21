package org.zhl.bean.beandefinition;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;

@Configurable
@Import({SelfBeanDefinitionRegister.class,RemoveBeanDefinitionPostProcessor.class})
public class BeanDefinitionConfig {
}
