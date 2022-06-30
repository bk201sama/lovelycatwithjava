package org.eu.bk201sama.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Objects;

@Slf4j
public class HttpUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String postJson(String api, String RequestJsonbean) throws IOException {
        OkHttpClient client = new OkHttpClient();
        log.info("HttpUtils:postJson:{}",RequestJsonbean);
        RequestBody body = RequestBody.create(JSON, RequestJsonbean);
        Request request = new Request.Builder().url(api).post(body).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }





}
