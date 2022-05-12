package com.y.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class HttpGetSample {

    private static final Logger logger = LoggerFactory.getLogger(HttpGetSample.class);

    /**
     * 调用shr的url获取文件
     * fileUrl = "https://hr-dev.test.hihonor.com/allowance/openApi/shr/file/byte/SrEAAAGEmEX0r08D";
     *
     * @param fileUrl 获取文件的路径
     * @return key 文件名  value 文件byte[]
     */
    public static Pair<String, byte[]> doGetRequestToShr(String fileUrl) {
        logger.info("开始调用shr文件url:{}",fileUrl);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String rst;
        byte[] fileContent = null;
        String fileName = "";
        try {
            httpClient = HttpClients.createDefault();
            URI uri = new URIBuilder(fileUrl).build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                rst = EntityUtils.toString(resEntity, StandardCharsets.UTF_8);
                JSONObject obj = JSON.parseObject(rst);
                JSONObject data = obj.getJSONObject("data");
                fileName = data.getString("fileName");
                fileContent = data.getObject("fileContent", byte[].class);
            }
            // 销毁
            EntityUtils.consume(resEntity);
        } catch (Exception e) {
            logger.error("调用shr的文件url出错啦{}",e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Pair<>(fileName, fileContent);
    }
}
