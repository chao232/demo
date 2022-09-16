package com.utils;

import cn.hutool.json.JSONUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CompareUtil {
    public static void compareMap(Map expectMap, Map actualMap, StringBuilder error, String ... notCompare){
        if (expectMap.size() != actualMap.size()) {
            error.append("交易条数不符，expectMap.size()=" + expectMap.size() + ",actualMap.size()=" + actualMap.size());
        }
        for (Object o : actualMap.keySet()) {
            if(expectMap.get(o)==null){
                continue;
            }
            error.append(compare(expectMap.get(o),actualMap.get(o)));
        }
    }
    public static void compareList(List expectList, List actualList,StringBuilder error, String ... notCompare){
        if (expectList.size() != actualList.size()) {
            error.append("交易条数不符，expectList.size()=" + expectList.size() + ",actualList.size()=" + actualList.size());
        }
        for (Object exp : expectList) {
            if(exp==null){
                continue;
            }
            for (Object actual : actualList) {
                error.append(compare(exp,actual));
            }
        }
    }
    public static String compare(Object exp,Object actual,String ... notCompare) {
        StringBuilder error=new StringBuilder();
        List<String> notCompareList = Arrays.asList(notCompare);
        Class<?> expClass = exp.getClass();
        Field[] expFields = expClass.getDeclaredFields();
        if(actual==null){
            return error.append(expClass).append("为空").toString();
        }
        if(expClass.getName().indexOf("Map")!=-1){
            Map<String,?> expectMap = (Map<String,?>) exp;
            Map<String,?> actualMap = (Map<String,?>) actual;
            compareMap( expectMap, actualMap, error);
            return error.toString();
        }
        if(expClass.getName().indexOf("List")!=-1){
            List<?> expectList = (List<?>) exp;
            List<?> actualList = (List<?>) actual;
            compareList( expectList, actualList, error);
            return error.toString();
        }
        for (Field field : expFields) {
            field.setAccessible(true);
            if( Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if(notCompareList.contains(field.getName())){
                continue;
            }
            try {
                Object actualValue = field.get(actual);
                Object expValue = field.get(exp);
                if(expValue==null){
                    continue;
                }
                if(actualValue==null){
                    error.append(actual.getClass().getName()+"--"+field.getName() + "不一致,实际值=" + actualValue +",期望值=" + expValue+"\n" );
                    continue;
                }
                if(typeMatch(field.getType().getName(),expValue)){
                    continue;
                }
                if(!JSONUtil.toJsonStr(expValue).equals(JSONUtil.toJsonStr(actualValue))){
                    error.append(actual.getClass().getName()+"--"+field.getName() + "不一致,实际值=" + actualValue +",期望值=" + expValue +"\n");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return error.toString();
    }    public static Result checkResult(StringBuilder error){
        if(StringUtils.isNotEmpty(error.toString())){
            return new Result(false,error.toString());
        }else {
            return new Result(true,error.toString());
        }
    }

    public static Boolean typeMatch(String name,Object expValue){
        List<String> types = Arrays.asList("int","long","java.lang.Long","java.lang.Integer");
        if(types.contains(name)&&expValue.hashCode()==0){
            return true;
        }else {
            return false;
        }
    }

}
