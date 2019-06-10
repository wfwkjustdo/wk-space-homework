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
    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish(Object service,int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();//BIO处理
                //每一个socket交给一个processorHandler来处理
                executorService.execute(new ProcessorHandler(socket,port));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
