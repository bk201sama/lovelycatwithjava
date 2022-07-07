package org.eu.bk201sama.constant;

import org.eu.bk201sama.event.EventFriendMsgEvent;
import org.eu.bk201sama.event.EventGroupMsgEvent;

public enum ProcessEnum {
    MENU("0"),
    CHAT("1");
    private String code;

    ProcessEnum(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return this.code;
    }
}
