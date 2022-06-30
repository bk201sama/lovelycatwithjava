package org.eu.bk201sama.event;

import lombok.Getter;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.springframework.context.ApplicationEvent;

public class EventBaseMsgEvent extends ApplicationEvent {
    @Getter
    private LovelyCatMessageDTO lovelyCatMessageDTO;
    public EventBaseMsgEvent(Object source, LovelyCatMessageDTO lovelyCatMessageDTO) {
        super(source);
        this.lovelyCatMessageDTO = lovelyCatMessageDTO;
    }

}
