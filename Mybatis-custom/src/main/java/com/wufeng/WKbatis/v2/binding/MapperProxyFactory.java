package com.wufeng.WKbatis.v2.binding;

import com.wufeng.WKbatis.v2.session.DefaultSqlSession;

import java.lang.reflect.Proxy;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 17:15
 * @Description 用于产生MapperProxy代理类
 **/
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;
    private Class object;

    public MapperProxyFactory(Class<T> mapperInterface, Class object) {
        this.mapperInterface = mapperInterface;
        this.object = object;
    }

    public T newInstance(DefaultSqlSession sqlSession){
        return (T) Proxy.newProxyInstance(mapperInterface.getClass().getClassLoader(),
                new Class[]{mapperInterface},
                new MapperProxy(sqlSession,object));
    }

}
