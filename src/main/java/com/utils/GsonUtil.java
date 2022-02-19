package com.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class GsonUtil {


     public static Gson gson = null;

     static JsonParser jsonParser = null;

    /**
     * 判断gson对象是否存在了,不存在则创建对象
     */
    static {
        if (gson == null) {
            // 当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        }

        if(jsonParser == null ){
            jsonParser = new JsonParser();
        }
    }

    private GsonUtil() {}

    /**
     * json 转对象
     * @param strJson
     * @return
     */
    public static JsonObject string2Object(String strJson) {
        return jsonParser.parse(strJson).getAsJsonObject();
    }


    /**
     * 将对象转成json格式
     * @param object
     * @return String
     */
    public static String object2String(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将json转成特定的cls的对象
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T stringToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            //传入json对象和对象类型,将json转成对象
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * json字符串转成list
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> stringToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            //根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * json字符串转成list中有map的
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> stringToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * json字符串转成map的
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> stringToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
    /**
     * map转对象
     * @param
     * @return
     */
    public static <T> T mapToBean(Map<String, ? extends Object> map, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t =  gson.fromJson(JSON.toJSONString(map), cls);
        }
        return t;
    }
    /**
     * map转 IPage
     * @param
     * @return
     */
    public static <T> IPage<T> mapToIPage(Map<String, Object> map,Class<T> cls) {

        IPage<T> page = new Page<>();
        if (gson != null) {
            page.setRecords(gson.fromJson(gson.toJson(map.get("records")), new TypeToken<List<T>>(){}.getType()));
        }
        return page;
    }

}

