package org.zhl.bean.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author zhanghanlin
 * @date 2022/4/29
 **/
@Component
public class SelfPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void register(String username) {
        System.out.println("准备发布！");

        publisher.publishEvent(new SelfSuccessEvent("hello"));
    }

}
