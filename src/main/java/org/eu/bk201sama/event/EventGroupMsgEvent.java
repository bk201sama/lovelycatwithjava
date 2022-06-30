package org.eu.bk201sama.event;

import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.springframework.context.ApplicationEvent;

public class EventGroupMsgEvent extends EventBaseMsgEvent {
    public EventGroupMsgEvent(Object source, LovelyCatMessageDTO lovelyCatMessageDTO) {
        super(source, lovelyCatMessageDTO);
    }
}
