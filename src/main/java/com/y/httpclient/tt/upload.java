package com.y.httpclient.tt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.entity.ContentType.create;

public class upload {

    public static Logger logger = new Logger();


    /**
     * 上传附件
     *
     * @param host
     * @param uri
     * @param filePath 文件路径
     * @param name     服务端定义的附件名
     * @param param    请求带的参数
     * @return
     */
    public String upload(String host, String uri, String filePath, String name, Map<String, String> param) {
        logger.info("*****************request*****************");
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String url = host + uri;
        String rst = "";
        try {
            httpClient = HttpClients.createDefault();
            //logger.info("请求路径： "+ url);
            HttpPost httpPost = new HttpPost(url);
            //经过当前类的加载器来加载资源，调用getResourceAsStream()是保证和类类型同一个加载器加载
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
            File file = new File(filePath);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create(); //建立MultipartEntityBuilder对象
            //添加上传的二进制文件
            builder.addBinaryBody(name, inputStream, create("multipart/form-data", Consts.UTF_8), file.getName());
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                StringBody stringBody = new StringBody(value, create("text/plain", Consts.UTF_8)); //解决中文乱码问题
                //添加请求入参
                builder.addPart(key, stringBody);
                logger.info("请求参数：" + key + ":" + value);
            }
            HttpEntity reqEntity = builder.build();
            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应内容
                rst = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                logger.info("*****************response*****************");
                logger.info("响应结果： " + rst);
            }
            // 销毁
            EntityUtils.consume(resEntity);
            return rst;
        } catch (Exception e) {
            logger.info("出错啦： " + e.getMessage());
            e.printStackTrace();
            return "出错了";
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
    }


    public static void main(String[] args) {
        doGet();
    }

    public static void dopost() {
        Map<String, String> param = new HashMap<>();
        param.put("attachId", "SrEAAAGEmEX0r08D");
        param.put("type", "MIA");
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String url = "https://hr-dev.test.hihonor.com/allowance/openApi/shr/file/byte";
        String rst = "";
        try {
            httpClient = HttpClients.createDefault();
            //logger.info("请求路径： "+ url);
            HttpPost httpPost = new HttpPost(url);
            //设置boundary,文件解析用,不可省略
            httpPost.addHeader("Content-Type", "application/json");
            //经过当前类的加载器来加载资源，调用getResourceAsStream()是保证和类类型同一个加载器加载
            MultipartEntityBuilder builder = MultipartEntityBuilder.create(); //建立MultipartEntityBuilder对象
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                StringBody stringBody = new StringBody(value, create("text/plain", Consts.UTF_8)); //解决中文乱码问题
                //添加请求入参
                builder.addPart(key, stringBody);
                logger.info("请求参数：" + key + ":" + value);
            }
            HttpEntity reqEntity = builder.build();
            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应内容
                rst = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                logger.info("*****************response*****************");
                logger.info("响应结果： " + rst);
            }
            // 销毁
            EntityUtils.consume(resEntity);
            System.out.println(rst);
        } catch (Exception e) {
            logger.info("出错啦： " + e.getMessage());
            e.printStackTrace();
            logger.info("出错了");
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
    }

    public static JSONArray doGet() {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        //String url = "https://hr-dev.test.hihonor.com/allowance/openApi/shr/file/byte/SrEAAAGEmEX0r08D?type=MIA";
        String rst = "";
        JSONArray fileContent = null;
        try {
            httpClient = HttpClients.createDefault();
            // 定义请求的参数
            URI uri = new URIBuilder("https://hr-dev.test.hihonor.com/allowance/openApi/shr/file/byte/SrEAAAGEmEX0r08D").setParameter("type", "MIA").build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应内容
                //InputStream st = resEntity.getContent();
                rst = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                JSONObject obj = JSONObject.parseObject(rst);
                JSONObject data = obj.getJSONObject("data");
                String fileName = data.getString("fileName");
                fileContent = data.getJSONArray("fileContent");
                logger.info("*****************response*****************");
                logger.info("响应结果： " + rst.substring(0,10));
                
            }
            // 销毁
            EntityUtils.consume(resEntity);
            //System.out.println(rst);
        } catch (Exception e) {
            logger.info("出错啦： " + e.getMessage());
            e.printStackTrace();
            logger.info("出错了");
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
        return fileContent;
    }

}
