package com.shenzc.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpAPIService {
    private static final Logger logger = LoggerFactory.getLogger(HttpAPIService.class);

    @Resource
    private CloseableHttpClient closeableHttpClient;

    /**
     * 不带参数的get请求,，状态码200，返回body，否则，返回null
     * @param url
     * @return
     * @throws IOException
     */
    public String doGet(String url) throws IOException {
        //声明http get 请求
        HttpGet httpGet = new HttpGet(url);

        //发起请求
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }else {
            logger.error(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }

        return null;
    }


    /**
     * 带参数的get请求
     * @param url
     * @param map
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, Object> map) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 带参数post请求
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public String doPost(String url, Map<String, Object> map) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //判断map是否为空，不为空则封装form表单
        if (map != null) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            //构建form表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"UTF-8");
            //把表单放到post
            httpPost.setEntity(urlEncodedFormEntity);
        }
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        else {
            logger.error(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }
        return null;
    }

    /**
     * 带参数post请求
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public String doPostByRAW(String url, Map<String, Object> map) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //判断map是否为空，不为空则封装form表单
        if (map != null) {
            JSONObject jsonParam = new JSONObject();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jsonParam.put(entry.getKey(), entry.getValue());
            }
            StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }else {
            logger.error(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }
        return null;
    }

    public String doPostByRAW(String url, String param) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //判断map是否为空，不为空则封装form表单
        StringEntity entity = new StringEntity(param,"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }else {
            logger.error(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }
        return null;
    }

    /**
     * 不带参数的post请求
     * @param url
     * @return
     * @throws IOException
     */
    public String doPost(String url) throws IOException {
        return doPost(url, null);

    }
}
