package com.y.httpclient;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    private static final String serverurl = "127.0.0.1:8888";
    private static final String CHECK_SERVER_URL_PATH = "check/serverConnection";
    private static final String CHECK_USER_PWD_PATH = "check/checkUserAndPwd";

    @Test
    public void connectTest232() {
        String serverUrl = serverurl;
        if (!serverUrl.endsWith("/")) {
            serverUrl += "/";
        }
        String finalServerUrl = serverUrl + CHECK_SERVER_URL_PATH;
        try {
            String response = HttpUtil.get(finalServerUrl + "", 30000);
            Response responseBean = JSONUtil.toBean(response, Response.class);
            if (responseBean.getCode() != 0) {
                System.out.println("Server connect failed!");
            } else {
                System.out.println("Server connected!");
            }
        } catch (Exception ex) {
            System.out.println("Server connect error!");
        }
    }

    @Test
    public void loginTest() {
        String serverUrl = serverurl;
        if (!serverUrl.endsWith("/")) {
            serverUrl += "/";
        }
        String finalServerUrl = serverUrl + CHECK_USER_PWD_PATH;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("user", "rykd");
            params.put("pwd", "rykd");
            String response = HttpUtil.get(finalServerUrl + "", params, 30000);
            Response responseBean = JSONUtil.toBean(response, Response.class);
            if (responseBean.getCode() != 0) {
                System.out.println("Server connect failed!");
            } else {
                System.out.println("Server connected!");
            }
        } catch (Exception ex) {
            System.out.println("Server connect error!");
        }
    }


}
