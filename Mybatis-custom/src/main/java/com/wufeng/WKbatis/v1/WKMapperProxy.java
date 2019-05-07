package com.wufeng.WKbatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author wangkai
 * @CreateTime 2019/5/7 23:25
 **/
public class WKMapperProxy implements InvocationHandler {
    private WKSqlSession sqlSession;

    public WKMapperProxy(WKSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        return sqlSession.selectOne(statementId, args[0]);
    }
}
