package org.eu.bk201sama.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.eu.bk201sama.core.ThreadValuePool;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.eu.bk201sama.event.EventFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

@RestController
@Slf4j
public class LovelyCatMsgRecController {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    @Resource
    private EventFactory eventFactory;
    /**
     * this is Synchronize method for quick test lovelycat is valid,paste url in ihttp plugin
     * @param message
     * @return how to answer the event
     */
    @PostMapping("/demo")
    @CrossOrigin(origins = "*")
    public HashMap<String,Object> demoTest(@RequestBody HashMap<String,String> message) {
        log.info("rec:{}", JSONUtil.toJsonPrettyStr(message));
        HashMap<String,Object> ret = new HashMap<>();
        ret.put( "success",true);
        ret.put("message","successful!");
        ret.put("event","SendTextMsg");
        ret.put("robot_wxid",message.get("robot_wxid"));
        ret.put("to_wxid",message.get("from_wxid"));
        ret.put("msg","you are big sha zi！");
        return ret;
    }

    /**
     * async operate event
     * @param message
     * @return
     */
    @PostMapping("/msg")
    @CrossOrigin(origins = "*")
    public void getMsgFromLovelyCat(@RequestBody LovelyCatMessageDTO message) {
        if(log.isDebugEnabled()){
            log.debug("getMsgFromLovelyCat[LovelyCatMessageDTO]:rec:{}", JSONUtil.toJsonPrettyStr(message));
        }
        Object eventObj = eventFactory.buildEventByLovelyCatMessageDTO(message);
        applicationEventPublisher.publishEvent(eventObj);
    }
}
