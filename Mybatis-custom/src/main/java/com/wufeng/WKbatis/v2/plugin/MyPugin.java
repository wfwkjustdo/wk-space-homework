package com.wufeng.WKbatis.v2.plugin;

import com.wufeng.WKbatis.v2.annotation.Intercepts;

import java.util.Arrays;

/**
 * @Author wangkai
 * @CreateTime 2019/5/24 22:19
 * @Description
 **/
@Intercepts("query")
public class MyPugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        Class pojo = (Class) invocation.getArgs()[2];
        System.out.println("插件输出：SQL：[" + statement + "]");
        System.out.println("插件输出：Parameters：[" + Arrays.toString(parameter) + "]");

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
