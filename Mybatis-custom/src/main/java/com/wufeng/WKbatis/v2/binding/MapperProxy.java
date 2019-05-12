package com.wufeng.WKbatis.v2.binding;

import com.wufeng.WKbatis.v2.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 18:32
 * @Description
 **/
public class MapperProxy implements InvocationHandler {
    private DefaultSqlSession sqlSession;
    private Class object;

    public MapperProxy(DefaultSqlSession sqlSession, Class object) {
        this.sqlSession = sqlSession;
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mappInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mappInterface + "." + methodName;
        //如果根据接口类型+方法名能找到映射的SQL，则执行SQL
        if(sqlSession.getConfiguration().hasStatement(statementId)){
            return sqlSession.selectOne(statementId,args,object);
        }
        //否则执行被代理对象的原方法
        return method.invoke(proxy,args);
    }
}
