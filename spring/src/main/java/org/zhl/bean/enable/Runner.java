package org.zhl.bean.enable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhanghanlin
 * @date 2022/4/29
 **/
public class Runner {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("org.zhl.bean.enable");
        final EnableBoss bean = context.getBean(EnableBoss.class);
        bean.testTheEnable();
        context.close();
    }
}
