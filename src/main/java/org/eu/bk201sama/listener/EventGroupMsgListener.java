package org.eu.bk201sama.listener;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eu.bk201sama.service.MessageAnswerService;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.eu.bk201sama.event.EventFriendMsgEvent;
import org.eu.bk201sama.vo.LovelyCatMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 群消息事件
 */
@ConditionalOnProperty(  value = {"iHttp.url"},
        matchIfMissing = false)
@Component
@Slf4j
public class EventGroupMsgListener implements ApplicationListener<EventFriendMsgEvent> {
    @Value("${iHttp.url}")
    private String url;
    @Resource
    private MessageAnswerService messageAnswerService;
    @Override
    public void onApplicationEvent(EventFriendMsgEvent eventFriendMsgEvent) {
        LovelyCatMessageDTO lovelyCatMessageDTO = eventFriendMsgEvent.getLovelyCatMessageDTO();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpUtil.post(url,mapper.writeValueAsString(LovelyCatMessageVO.builder()
                            .robotId(lovelyCatMessageDTO.getRobotId())
                            .msg(messageAnswerService.getAnswer(eventFriendMsgEvent.getLovelyCatMessageDTO().getMsg()))
                            .event(LovelyCatResponseEventEnum.SendTextMsg)
                            .toId(lovelyCatMessageDTO.getFromUserId())
                    .build()));
        } catch (Exception e) {
            log.error("EventGroupMsgEvent:onApplicationEvent:error:{}",e);
        }
    }
}
