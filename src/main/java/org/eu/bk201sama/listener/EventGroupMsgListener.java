package org.eu.bk201sama.listener;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.eu.bk201sama.event.EventGroupMsgEvent;
import org.eu.bk201sama.util.HttpUtils;
import org.eu.bk201sama.vo.LovelyCatMessageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 群消息事件
 */
@ConditionalOnProperty(  value = {"iHttp.url"},
        matchIfMissing = false)
@Component
@Slf4j
public class EventGroupMsgListener implements ApplicationListener<EventGroupMsgEvent> {
    @Value("${iHttp.url}")
    private String url;
    @Override
    public void onApplicationEvent(EventGroupMsgEvent eventGroupMsg) {
        LovelyCatMessageDTO lovelyCatMessageDTO = eventGroupMsg.getLovelyCatMessageDTO();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpUtils.postJson(url,mapper.writeValueAsString(LovelyCatMessageVO.builder()
                            .robotId(lovelyCatMessageDTO.getRobotId())
                            .msg("you are smart person")
                            .event(LovelyCatResponseEventEnum.SendTextMsg)
                            .toId(lovelyCatMessageDTO.getFromUserId())
                    .build()));
        } catch (Exception e) {
            log.error("EventGroupMsgEvent:onApplicationEvent:error:{}",e);
        }
    }
}
