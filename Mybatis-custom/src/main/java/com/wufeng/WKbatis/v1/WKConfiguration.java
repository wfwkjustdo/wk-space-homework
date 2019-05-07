package com.wufeng.WKbatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * @Author wangkai
 * @CreateTime 2019/5/7 23:09
 **/
public class WKConfiguration {
    public static final ResourceBundle sqlMappings;

    static {
        sqlMappings = ResourceBundle.getBundle("v1sql");
    }


    public <T> T getMapper(Class clazz, WKSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new WKMapperProxy(sqlSession));
    }
}
