package com.wufeng.WKbatis.v2.plugin;

/**
 * @Author wangkai
 * @CreateTime 2019/5/24 21:56
 * @Description 拦截器接口，所有自定义蓝旗街必须实现此接口
 **/
public interface Interceptor {
    /**
     * 插件的核心逻辑实现
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 对被拦截对象进行代理
     * @param object
     * @return
     */
    Object plugin(Object object);
}
