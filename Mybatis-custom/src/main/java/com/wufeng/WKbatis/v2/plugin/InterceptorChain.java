package com.wufeng.WKbatis.v2.plugin;


import org.omg.PortableInterceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 16:54
 * @Description 拦截链，存放所有拦截器，和对代理对象进行循环代理
 **/
public class InterceptorChain {
    private final List<Interceptor> interceptors = new ArrayList<>();
    public void addInterceptor(Interceptor interceptor){
        interceptors.add(interceptor);
    }


    public boolean hasPlugin() {
        if (interceptors.size()==0){
           return false;
        }
        return true;
    }


    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }
}
