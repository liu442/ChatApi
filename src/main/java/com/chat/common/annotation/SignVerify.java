package com.chat.common.annotation;

import java.lang.annotation.*;

/**
 * 是否需要进行签名验证注解
 * @date 2020/4/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SignVerify {
    /**
     * 是否需要验证签名，默认是验证，true or false
     * @return
     */
    boolean value() default true;
}
