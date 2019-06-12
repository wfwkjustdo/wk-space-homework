package com.wufeng;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        IHelloService helloService = new HelloSeriveImpl();

        RpcProxyServer proxyServer = new RpcProxyServer();
        proxyServer.publisher(helloService,8080);
    }
}
