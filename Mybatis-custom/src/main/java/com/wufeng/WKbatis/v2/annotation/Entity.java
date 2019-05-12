package com.wufeng.WKbatis.v2.annotation;

import java.lang.annotation.*;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 17:59
 * @Description 用于注解接口，以映射返回的实体类
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    Class<?> value();
}
