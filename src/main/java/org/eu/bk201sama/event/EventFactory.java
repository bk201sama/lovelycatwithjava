package org.eu.bk201sama.event;

import lombok.extern.slf4j.Slf4j;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventFactory {
    public Object buildEventByLovelyCatMessageDTO(LovelyCatMessageDTO lovelyCatMessageDTO){
        try {
            return lovelyCatMessageDTO.getEvent().getEventClass().getDeclaredConstructor(Object.class,LovelyCatMessageDTO.class).newInstance(this,lovelyCatMessageDTO);
        } catch (Exception e) {
            log.error("buildEventByLovelyCatMessageDTO:error:{}",e);
        }
        return null;
    }
}
