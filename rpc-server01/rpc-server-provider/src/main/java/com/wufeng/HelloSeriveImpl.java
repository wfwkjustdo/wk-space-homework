package com.wufeng;

/**
 * @Author wangkai
 * @Create 2019/6/10-20:41.
 * @Description
 */
public class HelloSeriveImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("request in sayHello:" + content);
        return "say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("request in saveUser:" + user);
        return "say Hello:" + user;
    }
}
