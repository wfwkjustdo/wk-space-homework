package com.wufeng;

/**
 * @Author wangkai
 * @Create 2019/6/10-20:30.
 * @Description
 */
public interface IHelloService {
    String sayHello(String content);

    /**
     * 保存用户
     * @param user
     * @return
     */
    String saveUser(User user);
}
