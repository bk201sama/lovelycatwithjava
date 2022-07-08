package org.eu.bk201sama.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class MessageService {
    /**
     * 获取分词
     * @param msg
     * @return
     */
    public LinkedHashSet<String> getKeyWordFromStr(String msg){
        TokenizerEngine engine = TokenizerUtil.createEngine();
        Result ret = engine.parse(msg);
        LinkedHashSet<String> msgSet =new LinkedHashSet<>();
        while(ret.hasNext()){
            Word word = ret.next();
            msgSet.add(word.getText());
        }
        return msgSet;
    }


    /**
     * 获取at的information
     * @param lovelyCatMessageDTO
     * @return
     */
    public String getAtInformation(LovelyCatMessageDTO lovelyCatMessageDTO) {
        String [] msgArray = StrUtil.splitToArray(StrUtil.subBetweenAll(lovelyCatMessageDTO.getMsg(),"[","]")[0],",") ;
        String sourceMsg = null;
        if(msgArray.length>=3){
            sourceMsg = StrUtil.subAfter(msgArray[1], " ", false);
        }
        return sourceMsg;
    }
}
