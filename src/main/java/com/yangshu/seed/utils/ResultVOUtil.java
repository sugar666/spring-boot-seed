package com.yangshu.seed.utils;

import com.yangshu.seed.VO.ResultVO;
import com.yangshu.seed.enums.ResultEnum;

/**
 * Created by yangshu on 2020/3/22 19:10
 * Description：工具类
 */
public class ResultVOUtil {

    public static ResultVO<Object> success(Object obj){
        ResultVO<Object> res = new ResultVO<>();
        res.setCode(ResultEnum.SUCCESS.getCode());
        res.setMsg(ResultEnum.SUCCESS.getMessage());
        res.setData(obj);
        return res;
    }

    public static ResultVO<Object> success(){
        return success(null);
    }

    public static ResultVO successMsg(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(msg);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        return resultVO;
    }


    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO<Object> error(Integer code, String msg,Object data){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO errorMsg(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(msg);
        resultVO.setCode(ResultEnum.UNAUTHORIZED.getCode());
        return resultVO;
    }
}
