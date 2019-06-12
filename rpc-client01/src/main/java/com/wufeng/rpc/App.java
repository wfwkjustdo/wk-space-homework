package com.wufeng.rpc;

import com.wufeng.IHelloService;
import com.wufeng.RpcRequest;
import com.wufeng.User;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class,
                "localhost",
                8080);
        String result = iHelloService.sayHello("wufeng");
        User user = new User();
        user.setName("wufeng");
        user.setAge(18);
        String resultUser = iHelloService.saveUser(user);
        System.out.println(result);
        System.out.println(resultUser);
    }
}
