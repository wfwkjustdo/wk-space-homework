package com.wufeng;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangkai
 * @CreateTime 2019-06-16 22:51
 **/
@Configuration()
@ComponentScan(basePackages = "com.wufeng")
public class SpringConfig {
    @Bean(name = "gpRpcServer")
    public GpRpcServer gpRpcServer(){
        return new GpRpcServer(8080);
    }
}
