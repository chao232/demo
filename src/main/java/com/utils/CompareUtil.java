package com.utils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CompareUtil {
    public static String compare(Object exp,Object actual,String ... notCompare) {
        StringBuilder error=new StringBuilder();
        List<String> notCompareList = Arrays.asList(notCompare);
        Class<?> expClass = exp.getClass();
        Field[] expFields = expClass.getDeclaredFields();
        if(actual==null){
            return error.append(expClass).append("为空").toString();
        }
        for (Field field : expFields) {
            field.setAccessible(true);
            if(notCompareList.contains(field.getName())){
                continue;
            }
            try {
                Object actualValue = field.get(actual);
                Object expValue = field.get(exp);
                if(expValue==null){
                    continue;
                }
                if(typeMatch(field.getType().getName(),expValue)){
                    continue;
                }
                if(!expValue.toString().equals(actualValue.toString())){
                    error.append(field.getName() + "不一致,实际值=" + actualValue + "，期望值=" + expValue +"\n");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return error.toString();
    }
    public static Result checkResult(StringBuilder error){
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
