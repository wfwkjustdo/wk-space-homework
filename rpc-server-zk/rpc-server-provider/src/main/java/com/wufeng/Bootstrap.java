package com.wufeng;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author wangkai
 * @CreateTime 2019-08-31 11:37
 **/
public class Bootstrap {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext)context).start();
    }
}
