package org.eu.bk201sama.constant;

import org.eu.bk201sama.dto.GroupMessageDTO;

public enum LovelyCatResponseEventEnum {
    SendTextMsg("SendTextMsg",String.class),
    SendGroupMsgAndAt("SendGroupMsgAndAt", String.class);
    private String name;
    private Class clazz;

    LovelyCatResponseEventEnum(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }
    @Override
    public String toString() {
        return this.name;
    }

}
