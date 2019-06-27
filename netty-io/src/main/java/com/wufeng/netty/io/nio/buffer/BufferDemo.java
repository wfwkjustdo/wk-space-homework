package com.wufeng.netty.io.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wangkai
 * @Create 2019/6/26-18:51.
 * @Description
 */
public class BufferDemo {

    public static void main(String[] args) throws IOException {
        //这用的是文件IO处理
        FileInputStream fin = new FileInputStream("E://test.txt");

        //创建文件的操作管道
        FileChannel fc = fin.getChannel();

        //分配一个10个大小的缓冲区，说白了，就是分配一个10个大小的byte数组
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化", buffer);

        //先读一下
        fc.read(buffer);
        output("调用read()", buffer);

        //准备操作之前，先锁定操作范围
        buffer.flip();
        output("调用flip()", buffer);

        //判断有没有可读数据
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
        }
        output("调用get()", buffer);

        //可以理解为解锁
        buffer.clear();
        output("调用clear()", buffer);

        //最后吧管道关闭
        fc.close();

    }

    /**
     * 把这个缓冲里面试试状态给打印出来
     *
     * @param step
     * @param buffer
     */
    private static void output(String step, ByteBuffer buffer) {
        System.out.println(step + " : ");
        //容器，数组大小
        System.out.print("capacity: " + buffer.capacity() + ", ");
        //当前操作数据所在的位置，也可以叫做游标
        System.out.print("position: " + buffer.position() + ", ");
        //锁定值，flip，数据操作范围索引只能在position - limit 之间
        System.out.print("limit: " + buffer.limit() + ", ");
        System.out.println();
    }
}
