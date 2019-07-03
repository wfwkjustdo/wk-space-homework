package com.wufeng.netty.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @Author wangkai
 * @Create 2019/6/27-19:41.
 * @Description
 */
public class BufferSlice {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //缓冲区中的数据0-9
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        //改变子缓冲区的内容
        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());


        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }
}