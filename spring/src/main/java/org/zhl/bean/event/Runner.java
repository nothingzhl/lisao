package org.zhl.bean.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhanghanlin
 * @date 2022/4/29
 **/
public class Runner {



    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("org.zhl.bean.event");
        final SelfPublisher bean = context.getBean(SelfPublisher.class);
        bean.register("zhanghanlin");
        context.close();
    }
}
