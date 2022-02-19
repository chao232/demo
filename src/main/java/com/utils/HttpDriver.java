package com.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpContentTooLargeException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HttpDriver {

    private PostMethod postMethod;
    private GetMethod getMethod;
    private HttpClient httpClient;

    public HttpDriver(){
        this.httpClient=new HttpClient();
    }

    public void postJson(Map<String, Object> paramMap,String token,String xClientType) throws Exception {
        if (null != token) {
            postMethod.setRequestHeader("X-Access-Token", token);
            postMethod.setRequestHeader("Authorization", token);

        }
        if (null != xClientType) {
            postMethod.setRequestHeader("X-Client-Type", xClientType);
        }
        //设置header信息
        postMethod.setRequestHeader("Content-type", "application/json;charset=UTF-8");
        postMethod.setRequestHeader("accept", "application/json");

        // 发送含消息体的对象
        RequestEntity requestEntity = new StringRequestEntity(GsonUtil.object2String(paramMap), "application/json", "UTF-8");
        // 绑定实体关系
        postMethod.setRequestEntity(requestEntity);
    }
    //post请求
    public <T> Response<T>  doPost(String url,Map<String, Object> paramMap,String token,String xClientType )  {
        String responseResult=null;
        try {
            postMethod=new PostMethod(url);
            postJson(paramMap,token,xClientType);
            log.info("url:" + url);
            int resultCode =httpClient.executeMethod(postMethod);
            if (resultCode != HttpStatus.SC_OK) {
                throw new RuntimeException(String.format("调用失败，resultCode= %d ,url= %s ",resultCode,url));
            }
            InputStream responseBodyAsStream = postMethod.getResponseBodyAsStream();
            responseResult = new BufferedReader(new InputStreamReader(responseBodyAsStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
        }catch (HttpContentTooLargeException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论怎样释放连接
            postMethod.releaseConnection();
        }
        return result(responseResult);
    }
    public <T> Response<T> doPostWeb(String url ,Map<String, Object> paramMap,String token)  {
        return doPost( url , paramMap,token, "web");
    }
    /**
     * get请求
     */
    //get请求拼接
    private String getJson(String url,Map<String, String> paramMap) {
        //对url的query进行url编码
        List<BasicNameValuePair> params = new ArrayList<>();
        List<NameValuePair> paramsQuery = new ArrayList<>();

        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            paramsQuery.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        String newQueryString = URLEncodedUtils.format(params, StandardCharsets.UTF_8);

        newQueryString = url + "?" + newQueryString;
        NameValuePair[] strings = new NameValuePair[paramsQuery.size()];
        getMethod.setQueryString(paramsQuery.toArray(strings));
        return newQueryString;
    }
    public <T> Response<T> doGet(String url ,Map<String, String> paramMap,String token, String xClientType)  {
        String responseResult=null;
        try {
            getMethod=new GetMethod(url);
//            getMethod.setRequestHeader("x-forwarded-for", "10.113.81.71");
//            getMethod.setRequestHeader("Proxy-Client-IP", "10.113.81.72,10.113.81.73");

            if (null != token) {
                getMethod.setRequestHeader("X-Access-Token", token);
            }
            if (null != xClientType) {
                getMethod.setRequestHeader("X-Client-Type", xClientType);
            }
            if (null != paramMap) {
                url=getJson(url,paramMap);
            }
            log.info("url:" + url);
            int resultCode =httpClient.executeMethod(getMethod);
            if (resultCode != HttpStatus.SC_OK) {
                throw new RuntimeException(String.format("调用失败，resultCode= %d ,url= %s ",resultCode,url));
            }
            InputStream responseBodyAsStream = getMethod.getResponseBodyAsStream();
            responseResult = new BufferedReader(new InputStreamReader(responseBodyAsStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
        }catch (HttpContentTooLargeException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论怎样释放连接
            getMethod.releaseConnection();
        }
        return result(responseResult);
    }
    public <T> Response<T> doGetWeb(String url ,Map<String, String> paramMap,String token)  {
        return doGet( url , paramMap,token, "web");
    }
    public <T> Response<T> doGetNoParam(String url ,String token,String xClientType)  {
        return doGet( url , null,token, xClientType);
    }
    //结果处理
    public <T> Response<T> result(String responseResult){
        Response result = GsonUtil.stringToBean(responseResult, Response.class);
        log.info("调用返回:result={}",result);
        Assert.assertTrue(result.isSuccess(),result.getMsg());
        return result;
    }
}
