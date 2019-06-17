package com.wufeng;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangkai
 * @Create 2019/6/17-17:41.
 * @Description
 */
@Configuration
public class SpringConfig {
    @Bean(name="rpcProxyClient")
    public RpcProxyClient proxyClient(){
        return new RpcProxyClient();
    }
}
