package com.utils;

import lombok.Data;

@Data
public class Result {
    private  boolean success;
    private  String msg;
    Result(Boolean success, String msg){
        this.success=success;
        this.msg=msg;
    }
}
