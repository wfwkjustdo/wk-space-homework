package com.wufeng.netty.io.nio.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wangkai
 * @Create 2019/7/3-19:50.
 * @Description
 */
public class FileInputDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("E://test.txt");

        //获取通道
        FileChannel fc = fin.getChannel();

        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println((char) b);
        }
    }
}
