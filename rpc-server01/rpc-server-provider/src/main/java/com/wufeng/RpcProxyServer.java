package com.wufeng;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wangkai
 * @Create 2019/6/10-20:44.
 * @Description
 */
public class RpcProxyServer {
    //定义一个线程池，每接受一个请求分别使用一个线程进行接收处理
    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service,int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();//BIO处理
                //每一个socket交给一个processorHandler来处理
                executorService.execute(new ProcessorHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
