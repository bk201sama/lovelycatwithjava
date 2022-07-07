package org.eu.bk201sama.listener;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;
import org.eu.bk201sama.dto.AnswerDTO;
import org.eu.bk201sama.entity.User;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.eu.bk201sama.event.EventFriendMsgEvent;
import org.eu.bk201sama.service.ChatWithAIService;
import org.eu.bk201sama.service.UserService;
import org.eu.bk201sama.vo.LovelyCatMessageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

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
    private UserService userService;
    @Resource
    private ChatWithAIService chatWithAIService;
    @Override
    public void onApplicationEvent(EventFriendMsgEvent eventFriendMsgEvent) {
        if(!Objects.equals( eventFriendMsgEvent.getLovelyCatMessageDTO().getRobotId(), eventFriendMsgEvent.getLovelyCatMessageDTO().getToId())){
            if(log.isDebugEnabled()){
                log.debug("onApplicationEvent[eventFriendMsgEvent]:skip:{}", JSONUtil.toJsonPrettyStr(eventFriendMsgEvent));
            }
            return;
        }
        LovelyCatMessageDTO lovelyCatMessageDTO = eventFriendMsgEvent.getLovelyCatMessageDTO();
        ObjectMapper mapper = new ObjectMapper();
        try {
            AnswerDTO answerDTO = this.getAnswer(eventFriendMsgEvent.getLovelyCatMessageDTO());
            HttpUtil.post(url,mapper.writeValueAsString(LovelyCatMessageVO.builder()
                            .robotId(lovelyCatMessageDTO.getRobotId())
                            .msg(answerDTO.getMsg())
                            .event(answerDTO.getMsgType())
                            .toId(lovelyCatMessageDTO.getFromUserId())
                    .build()));
        } catch (Exception e) {
            log.error("EventGroupMsgEvent:onApplicationEvent:error:{}",e);
        }
    }



    private AnswerDTO getAnswer(LovelyCatMessageDTO lovelyCatMessageDTO) {
        String msg = chatWithAIService.chatWithQingYunKe(lovelyCatMessageDTO.getMsg());
        return AnswerDTO.builder().msg(msg).msgType(LovelyCatResponseEventEnum.SendTextMsg).build();
    }
}
