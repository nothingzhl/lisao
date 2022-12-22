package org.zhl.bean.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhanghanlin
 * @date 2022/4/29
 **/
public class SelfSuccessEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SelfSuccessEvent(Object source) {
        super(source);
    }
}
