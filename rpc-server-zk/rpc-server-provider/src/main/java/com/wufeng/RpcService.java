package com.wufeng;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author wangkai
 * @Create 2019/6/17-16:05.
 * @Description
 */
@Target(ElementType.TYPE)//类/接口
@Retention(RetentionPolicy.RUNTIME)
@Component//被spring进行扫描
public @interface RpcService  {
    Class<?> value();

    /**
     * 版本号
     * @return
     */
    String version();
}
