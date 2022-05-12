package com.y.httpclient.tt;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class post {

    /**
     *
     * @param url 请求URL
     * @param filePath 本地文件地址
     * @return
     */
    public static String upload(String url,String filePath){
        String fdfsPath = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            File file = new File(filePath);
            String name = file.getName();
            InputStream in = new FileInputStream(file);
            MultipartEntity reqEntity = new MultipartEntity();
            InputStreamBody inputStreamBody = new InputStreamBody(in,name);
            StringBody fileNam = new StringBody(name);
            StringBody dateFlag = new StringBody("20160122152301");
            StringBody datumType = new StringBody("0");
            StringBody uploadWay = new StringBody("0");
            StringBody userId = new StringBody("0538");
            StringBody tenderId = new StringBody("2315");
            StringBody metrialsType = new StringBody("25");
            StringBody ip = new StringBody("0.0.0.1");
            StringBody driverName = new StringBody("huawei");
            StringBody systemVersion = new StringBody("djf");
            StringBody position = new StringBody("信息路38",  Charset.forName("utf8"));
            //文件流            reqEntity.addPart("datums", inputStreamBody);
            reqEntity.addPart("fileName", fileNam);
            reqEntity.addPart("dateFlag", dateFlag);
            reqEntity.addPart("datumType", datumType);
            reqEntity.addPart("uploadWay", uploadWay);
            reqEntity.addPart("userId", userId);
            reqEntity.addPart("tenderId", tenderId);
            reqEntity.addPart("metrialsType", metrialsType);
            reqEntity.addPart("ip", ip);
            reqEntity.addPart("driverName", driverName);
            reqEntity.addPart("systemVersion", systemVersion);
            reqEntity.addPart("position", position);

            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK){

                System.out.println("服务器正常响应.....");

                HttpEntity resEntity = response.getEntity();
                System.out.println(EntityUtils.toString(resEntity));//httpclient自带的工具类读取返回数据

                System.out.println(resEntity.getContent());

                EntityUtils.consume(resEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        upload("http://192.168.1.1:8080/xxxImageUpload.action","E:\\weatertest\\002.jpg");
    }





}
