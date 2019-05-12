package com.wufeng.WKbatis.v2.annotation;

import java.lang.annotation.*;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 18:09
 * @Description 注解方法，配置SQL语句
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {
    String value();
}
