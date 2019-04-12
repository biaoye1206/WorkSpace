package com.tianj.autowash.basic;

import com.tianj.autowash.entity.device.DataTransfer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-03-12 14:33
 */
public class ContextMsgNotityEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ContextMsgNotityEvent(DataTransfer source) {
        super(source);
    }

}

@Component
class MsgNotityListenner implements ApplicationListener<ContextMsgNotityEvent> {
    @Override
    public void onApplicationEvent(ContextMsgNotityEvent event) {
        System.out.println(event.getSource());
    }
}