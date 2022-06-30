package org.eu.bk201sama.constant;

import org.eu.bk201sama.event.EventFriendMsgEvent;
import org.eu.bk201sama.event.EventGroupMsgEvent;

public enum LovelyCatRequestEventEnum {
    EventGroupMsg("EventGroupMsg",EventGroupMsgEvent.class),
    EventFriendMsg("EventFriendMsg", EventFriendMsgEvent.class)
    ;
    private String name;
    private Class clazz;

    LovelyCatRequestEventEnum(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }
    @Override
    public String toString() {
        return this.name;
    }

    public Class getEventClass(){
        return this.clazz;
    }
}
