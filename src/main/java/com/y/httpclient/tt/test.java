package com.y.httpclient.tt;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class test {

    /**
     * 用于下载文件
     * flag : 用于数据摆渡 ， 输出流转换输入流  true为转换：：：： 输出流转换输入流
     * <p>
     * 为false写到文件中
     */
    public static Map<String, Object> doPostDownLoad(String url, Map params, OutputStream fos, boolean flag) {
        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setHeader("Content-Type", "*/*");
            request.setURI(new URI(url));
            //设置参数
            request.setEntity(new StringEntity(JSONObject.toJSONString(params)));
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {    //请求成功
                Map<String, Object> mapResult = new HashMap<String, Object>();
                mapResult.put("isSuccess", true);
                if (flag) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    response.getEntity().writeTo(bos);
                    ByteArrayInputStream swapInputStream = new ByteArrayInputStream(bos.toByteArray());
                    mapResult.put("inputStream", swapInputStream);

                } else {
                    response.getEntity().writeTo(fos);
                }
                return mapResult;
            } else {   //
                System.out.println("状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求（用于请求file传输）
     *
     * @param url
     * @param instream
     * @return
     */
    public static String doPost(String url, InputStream instream) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/octet-stream");
        httpPost.setHeader("Content-Type", "application/octet-stream");
        String charSet = "UTF-8";
        CloseableHttpResponse response = null;
        httpPost.setEntity(new InputStreamEntity(instream));
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                System.out.println("请求返回:" + state + "(" + url + ")");
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<Object, Object> param = new HashMap<>();
        param.put("attachId","SrEAAAGEmEX0r08D");
        param.put("type","MIA");
        doPostDownLoad("https://hr-dev.test.hihonor.com/allowance/common/upload/shrFile",param,null,true);
    }


    /**
     * 盖章文件Pdf下载
     * @Param url 请求下载地址
     * @Param accessToken token
     * */
    public HttpEntity pdfDownload(String url,String accessToken){
        HttpEntity entity = null;
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type","application/json;charset=UTF-8");
            httpGet.setHeader("Authorization","Bearer " + accessToken);
            HttpResponse response = httpClient.execute(httpGet);
            entity = response.getEntity();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }


    /**
     * 输出pdf
     * @Param entity 请求返回内容
     * @Param filePath 文件保存地址
     * */
    public long writeToPdf(HttpEntity entity,String filePath)throws Exception{
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        int size = 0;
        try{
            byte[] bytes = EntityUtils.toByteArray(entity);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer,0,length);
                length = bis.read(buffer);
            }
            bos.flush();
            return bis.available();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                bos.close();
                fos.close();
                bis.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return size;
    }

}
