package com.wufeng.starter.autoconfiguration;

import com.wufeng.starter.ZookeeperTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author wangkai
 * @Create 2019/8/13-17:58.
 * @Description
 */
@EnableConfigurationProperties({ZookeeperProperties.class})
@Configuration
public class ZookeeperAutoConfiguration {

    @Bean
    public ZookeeperTemplate zookeeperTemplate(ZookeeperProperties zookeeperProperties){

        return new ZookeeperTemplate(zookeeperProperties);
    }
}
