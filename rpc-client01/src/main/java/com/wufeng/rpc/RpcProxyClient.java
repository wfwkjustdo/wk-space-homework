package com.wufeng.rpc;

import java.lang.reflect.Proxy;

/**
 * @Author wangkai
 * @Create 2019/6/12-9:44.
 * @Description
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls},
                new RemoteInvocationHandler(host, port));
    }
}
