package org.eu.bk201sama.event;

import org.eu.bk201sama.dto.LovelyCatMessageDTO;

public class EventFriendMsgEvent extends EventBaseMsgEvent {

    public EventFriendMsgEvent(Object source, LovelyCatMessageDTO lovelyCatMessageDTO) {
        super(source, lovelyCatMessageDTO);
    }
}
