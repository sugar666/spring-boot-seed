package com.yangshu.seed.annotation;


import com.yangshu.seed.enums.AuthAopEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Package shortvideo
 *
 * @author yangshu on 2020/10/6 09:07
 * Description：用户权限注解
 */

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UserAuthentication {
    /**
     *  true为启用验证
     *  false为跳过验证
     * @return
     */
    boolean pass() default true;

    AuthAopEnum role() default AuthAopEnum.ANON;

}
