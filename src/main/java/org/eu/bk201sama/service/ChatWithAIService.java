package org.eu.bk201sama.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lilittlecat.chatgpt.ChatGPT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class ChatWithAIService {
    @Value("${qingyunke.url}")
    private String qingyunkeUrl;

    public String chatWithQingYunKe(String msg) {
        String requestUrl = qingyunkeUrl + URLEncoder.encode(msg);
        String ret = HttpUtil.get(requestUrl);
        if(JSONUtil.isJson(ret)){
            JSONObject jsonRet = JSONUtil.parseObj(ret);
            return jsonRet.getByPath("content",String.class);
        }
        return null;
    }

    public String chatWithchatGPT(String msg) {
        ChatGPT chatGPT = new ChatGPT("");
        String ret = chatGPT.ask(msg);
        return ret;
    }
}
