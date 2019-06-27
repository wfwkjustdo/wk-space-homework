package com.wufeng.netty.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author wangkai
 * @Create 2019/6/26-14:30.
 * @Description 同步阻塞IO模型
 */
public class BIOServer {
    /**
     * 服务器网络IO模型的封装对象
     */
    ServerSocket server;

    public BIOServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("BIo服务已经启动，监听端口是：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始监听，并处理逻辑
     */
    public void listen() throws IOException {
        while (true) {
            //等待客户端连接，阻塞方法
            //socket数据发送者在服务端的引用
            Socket client = server.accept();
            System.out.println("客户端的连接端口为：" + client.getPort());

            //对方数据给我了，读 Input
            InputStream is = client.getInputStream();

            //网卡客户端把数据发送到网卡，机器监听所得到的数据读到JVM内中
            byte[] buff = new byte[1024];
            int len = is.read(buff);
            if (len > 0) {
                String msg = new String(buff, 0, len);
                System.out.println("收到" + msg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(8080).listen();
    }
}
