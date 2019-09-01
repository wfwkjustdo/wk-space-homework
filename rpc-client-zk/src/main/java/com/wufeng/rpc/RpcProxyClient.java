package com.wufeng.rpc;

import java.lang.reflect.Proxy;

/**
 * @Author wangkai
 * @Create 2019/6/17-16:59.
 * @Description
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls},
                new RemoteInvocationHandler(host, port));
    }
}
