package com.wufeng.WKbatis.v2.annotation;

import java.lang.annotation.*;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 18:10
 * @Description 用于注解拦截器，指定拦截的方法
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    String value();
}
