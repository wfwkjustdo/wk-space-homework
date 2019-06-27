package com.wufeng.netty.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @Author wangkai
 * @Create 2019/6/26-14:31.
 * @Description
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {

        /**
         * 好和谁进行通讯，服务器IP、服务器端口
         * 一台机器的端口号是有限的
         */
        Socket client = new Socket("localhost", 8080);
        System.out.println("客户端随机生成的端口为：" + client.getLocalPort());

        /**
         * 输出 O write();
         * 不管是客户端还是服务端，都有可能write和read
         */
        OutputStream os = client.getOutputStream();

        //生成一个随机的ID
        String name = UUID.randomUUID().toString();

        System.out.println("客户端发送的数据：" + name);
        os.write(name.getBytes());
        os.close();
        client.close();

    }

}
