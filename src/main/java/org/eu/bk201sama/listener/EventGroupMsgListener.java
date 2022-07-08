package org.eu.bk201sama.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;
import org.eu.bk201sama.dto.AnswerDTO;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.eu.bk201sama.event.EventGroupMsgEvent;
import org.eu.bk201sama.service.ChatWithAIService;
import org.eu.bk201sama.service.UserService;
import org.eu.bk201sama.vo.LovelyCatMessageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
    @Resource
    private UserService userService;
    @Resource
    private ChatWithAIService chatWithAIService;
    @Override
    public void onApplicationEvent(EventGroupMsgEvent eventGroupMsgEvent) {
        if(!checkTriggerCondition(eventGroupMsgEvent)){
            if(log.isDebugEnabled()){
                log.debug("onApplicationEvent[eventFriendMsgEvent]:skip:{}", JSONUtil.toJsonPrettyStr(eventGroupMsgEvent));
            }
            return;
        }
        LovelyCatMessageDTO lovelyCatMessageDTO = eventGroupMsgEvent.getLovelyCatMessageDTO();
        ObjectMapper mapper = new ObjectMapper();
        try {
            AnswerDTO answerDTO = this.getAnswer(eventGroupMsgEvent.getLovelyCatMessageDTO());
            HttpUtil.post(url,mapper.writeValueAsString(LovelyCatMessageVO.builder()
                            .robotId(lovelyCatMessageDTO.getRobotId())
                            .groupId(lovelyCatMessageDTO.getFromId())
                            .memberId(lovelyCatMessageDTO.getFromUserId())
                            .memberName(lovelyCatMessageDTO.getFromUserName())
                            .msg(answerDTO.getMsg())
                            .event(answerDTO.getMsgType())
                            .toId(lovelyCatMessageDTO.getFromId())
                    .build()));
        } catch (Exception e) {
            log.error("EventGroupMsgEvent:onApplicationEvent:error:{}",e);
        }
    }

    private boolean checkTriggerCondition(EventGroupMsgEvent eventGroupMsgEvent) {
        String sourceMsg = eventGroupMsgEvent.getLovelyCatMessageDTO().getMsg();
        if(StrUtil.isWrap(StrUtil.trim(sourceMsg), '[', ']')){
           return checkIfAtRobot(eventGroupMsgEvent);
        }
        else {
            return false;
        }
    }

    private boolean checkIfAtRobot(EventGroupMsgEvent eventGroupMsgEvent) {
        String sourceMsg = eventGroupMsgEvent.getLovelyCatMessageDTO().getMsg();
        String robotId= eventGroupMsgEvent.getLovelyCatMessageDTO().getRobotId();
        String [] msgArray =StrUtil.splitToArray(StrUtil.subBetweenAll(sourceMsg,"[","]")[0],",") ;
        if(msgArray.length>=3){
            return Objects.equals("@at", msgArray[0]) && Objects.equals(robotId, StrUtil.subAfter(msgArray[2], "=", false));
        }
        return false;
    }


    private AnswerDTO getAnswer(LovelyCatMessageDTO lovelyCatMessageDTO) {
        String msg = chatWithAIService.chatWithQingYunKe(lovelyCatMessageDTO.getMsg());
        return AnswerDTO.builder().msg(msg).msgType(LovelyCatResponseEventEnum.SendGroupMsgAndAt).build();
    }

}
