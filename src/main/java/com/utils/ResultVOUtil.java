package com.utils;

import com.VO.ResultVO;

/**
 * Created by notaf
 * 2020/8/5 23:46
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
