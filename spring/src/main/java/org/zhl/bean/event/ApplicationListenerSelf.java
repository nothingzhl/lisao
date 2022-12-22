package org.zhl.bean.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhanghanlin
 * @date 2022/4/29
 **/
@Component
public class ApplicationListenerSelf implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("收到刷新event"+event);
    }

    @EventListener
    public void onEventClose(ContextClosedEvent event){
        System.out.println("收到对应的close event"+event);
    }

    @EventListener
    public void onSelfSuccessEvent(SelfSuccessEvent event){
        System.out.println("成功了"+event.getSource());
    }
}
