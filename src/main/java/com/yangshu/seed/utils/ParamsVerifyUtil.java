package com.yangshu.seed.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author yangshu on 2020/10/19 14:36
 * Description：验证post参数完整性
 */
public class ParamsVerifyUtil {

    public static String processErrorString(BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()).append(",");
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }
}
