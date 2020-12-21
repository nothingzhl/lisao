package org.zhl.bean.env;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@Configurable
@ComponentScan(basePackages = "org.zhl.bean.env")
@PropertySource("classpath:env/jdbc.properties")
public class EnvConfig {

}
