package com.VO;

import lombok.Data;

/**
http请求最外层对象
 */

@Data
public class ResultVO<T>{
    /*错误码*/
    private String code;
    private String msg;
    /*具体内容*/
    private T data;
}
