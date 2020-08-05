package com.enums;

import lombok.Getter;

/**
 * Created by notaf
 * 2020/8/3 23:26
 */
@Getter
public enum productStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架");
    private Integer code;
    private String message;
    productStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
