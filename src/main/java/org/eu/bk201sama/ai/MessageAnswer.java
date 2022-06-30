package org.eu.bk201sama.ai;


import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class MessageAnswer {
    @Value("${qingyunke.url}")
    private String url;

    public String getAnswer(String msg) {
        String requestUrl = url + URLEncoder.encode(msg);
        String ret = HttpUtil.get(requestUrl);
        if(JSONUtil.isJson(ret)){
            JSONObject jsonRet = JSONUtil.parseObj(ret);
            return jsonRet.getByPath("content",String.class);
        }
        return null;
    }
}
