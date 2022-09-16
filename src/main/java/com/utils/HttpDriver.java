package com.utils;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HttpDriver {
    @Autowired

    private PostMethod postMethod;
    private GetMethod getMethod;
    private HttpClient httpClient;
    public static final int cache = 10 * 1024;

    public HttpDriver(){
        this.httpClient=new HttpClient();
    }
    public <T> Response<T> postFileResult(MultipartFile file,  Map<String,String> headerParams,
                                          String url)  {
        return result(postFile(file, headerParams, url));
    }
    public String postFile(MultipartFile file,  Map<String,String> headerParams,
                           String url)  {
        MultipartFile[] f=new MultipartFile[1];
        f[0]=file;
        return postFile(f, headerParams, url);
    }
    public String postFile(MultipartFile[] file,  Map<String,String> headerParams,
                           String url)  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            //添加header
            for (Map.Entry<String, String> e : headerParams.entrySet()) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Consts.UTF_8);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//加上此行代码解决返回中文乱码问题
            for (MultipartFile multipartFile : file) {
                builder.addBinaryBody(multipartFile.getName(), multipartFile.getInputStream(),
                        ContentType.MULTIPART_FORM_DATA, multipartFile.getOriginalFilename());// 文件流
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
    public void postHeader(String content,Map<String, String> headerMap) throws Exception {
        for (String s : headerMap.keySet()) {
            postMethod.setRequestHeader(s,headerMap.get(s));
        }
        //设置header信息
        postMethod.setRequestHeader("Content-type", "application/json;charset=UTF-8");
        postMethod.setRequestHeader("accept", "application/json");

        // 发送含消息体的对象
        RequestEntity requestEntity = new StringRequestEntity(content, "application/json", "UTF-8");
        // 绑定实体关系
        postMethod.setRequestEntity(requestEntity);
    }
    //post请求
    public void doPostOri(String url,String content,Map<String, String> headerMap )  {
        try {
            postMethod=new PostMethod(url);
            postHeader(content,headerMap);
            log.info("url:" + url);
            int resultCode =httpClient.executeMethod(postMethod);
            if (resultCode != HttpStatus.SC_OK) {
                throw new RuntimeException(String.format("调用失败，resultCode= %d ,url= %s ",resultCode,url));
            }
        }catch (HttpContentTooLargeException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(String.format("调用失败,url= %s ",url));
        } finally {
        }
    }
    public String doPostOriString(String url,String content,Map<String, String> headerMap )  {
        String responseResult=null;
        try {
            doPostOri(url,content,headerMap);
            InputStream responseBodyAsStream = postMethod.getResponseBodyAsStream();
            responseResult = new BufferedReader(new InputStreamReader(responseBodyAsStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
        }catch (HttpContentTooLargeException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(String.format("调用失败,url= %s ",url));
        } finally {
            postMethod.releaseConnection();
        }
        return responseResult;
    }

    public <T> Response<T>  doPost(String url,Map<String, Object> paramMap,String token,String xClientType )  {
        url= getJson(url, paramMap);
        Map<String, String> headerMap= Maps.newHashMap();
        headerMap.put("X-Access-Token",token);
        headerMap.put("Cookie",token);
        headerMap.put("X-Client-Type",xClientType);
        return result(doPostOriString(url,JSONUtil.toJsonStr(paramMap),headerMap));
    }
    public <T> Response<T>  doPost(String url,String content,String token,String xClientType )  {
        Map<String, String> headerMap= Maps.newHashMap();
        headerMap.put("X-Access-Token",token);
        headerMap.put("Cookie",token);
        headerMap.put("X-Client-Type",xClientType);
        return result(doPostOriString(url,content,headerMap));
    }
    public <T> Response<T>  doPost(String url,Map<String, Object> paramMap,String token,String xClientType, String XChannelSource )  {
        url= getJson(url, paramMap);
        Map<String, String> headerMap= Maps.newHashMap();
        headerMap.put("X-Access-Token",token);
        headerMap.put("Cookie",token);
        headerMap.put("X-Client-Type",xClientType);
        headerMap.put("X-Channel-Source", XChannelSource);
//        if (!StringUtil.isEmpty(XChannelSource)){
//
//        }
        return result(doPostOriString(url, JSONUtil.toJsonStr(paramMap),headerMap));    }

    public <T> Response<T> doPostWeb(String url ,Map<String, Object> paramMap,String token)  {
        return doPost( url , paramMap,token, "web");
    }
    public String  doPostLogin(String url,Map<String, Object> paramMap,String token,String xClientType )  {
        StringBuffer cookie=new StringBuffer();
        try {
            url= getJson(url, paramMap);
            Map<String, String> headerMap= Maps.newHashMap();
            headerMap.put("X-Access-Token",token);
            headerMap.put("Cookie",token);
            headerMap.put("X-Client-Type",xClientType);
            doPostOri(url,GsonUtil.object2String(paramMap),headerMap);
            Cookie[] cookies = httpClient.getState().getCookies();
            for (Cookie c : cookies) {
                cookie.append(c);
            }
            postMethod.setRequestHeader("cookie",cookie.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论怎样释放连接
            postMethod.releaseConnection();
        }
        return cookie.toString();
    }


    /**
     * get请求
     */
    //请求拼接
    private String getJson(String url,Map<String, Object> paramMap) {
        //对url的query进行url编码
        List<BasicNameValuePair> params = new ArrayList<>();
        List<NameValuePair> paramsQuery = new ArrayList<>();

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            paramsQuery.add(new NameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        String newQueryString = URLEncodedUtils.format(params, StandardCharsets.UTF_8);

        newQueryString = url + "?" + newQueryString;
        NameValuePair[] strings = new NameValuePair[paramsQuery.size()];
        if(getMethod!=null){
            getMethod.setQueryString(paramsQuery.toArray(strings));
        }
        if(postMethod!=null){
            postMethod.setQueryString(paramsQuery.toArray(strings));
        }
        return newQueryString;
    }

    //增加非必填参数 xChannelSource ：app渠道
    public String doGetOri(String url ,Map<String, Object> paramMap,String token, String xClientType, String ...xChannelSource)  {
        String responseResult=null;
        String appSource =null;
        if(xChannelSource.length!=0 && StringUtils.isNotEmpty(xChannelSource.clone()[0])){
            appSource = xChannelSource.clone()[0];
        }
        try {
            getMethod=new GetMethod(url);
            if (null != token) {
                getMethod.setRequestHeader("X-Access-Token", token);
            }
            if (null != xClientType) {
                //如果请求类型是app,则添加来源渠道参数 xChannelSource : changePowerApp:换电app  marketingApp：营销sdk
                if (xClientType == "app"){
                    getMethod.setRequestHeader("X-Channel-Source", appSource);
                    getMethod.setRequestHeader("appVersion", "2.0.0");
                }else {
                    getMethod.setRequestHeader("X-Client-Type", xClientType);
                }

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
        return responseResult;
    }
    public  void download(String url ,Map<String, Object> paramMap,String token, String xClientType,String fileName)  {

        try {
            getMethod=new GetMethod(url);
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

            if(new File(fileName).exists()){
                new File(fileName).delete();
            }
            File file = new File(fileName);
            FileOutputStream fileout = new FileOutputStream(file);
            /**
             * 根据实际运行效果 设置缓冲区大小
             */
            byte[] buffer=new byte[cache];
            int ch = 0;
            while ((ch = responseBodyAsStream.read(buffer)) != -1) {
                fileout.write(buffer,0,ch);
            }
            responseBodyAsStream.close();
            fileout.flush();
            fileout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> Response<T> doGet(String url , Map<String, Object> paramMap, String token, String xClientType)  {
        return result(doGetOri(url,paramMap,token,xClientType));
    }

    //xChannelSource ：来源渠道
    public <T> Response<T> doGetApp(String url , Map<String, Object> paramMap, String token, String xClientType,String ...xChannelSource)  {
        return  result(doGetOri(url,paramMap,token,xClientType,xChannelSource));
    }

    public <T> Response<T> doGetWeb(String url ,Map<String, Object> paramMap,String token)  {

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
    //post请求

}
