package com.wufeng.netty.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @Author wangkai
 * @Create 2019/6/27-20:59.
 * @Description 只读缓冲区
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //缓冲区中的数据0-9
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //穿件只读缓冲区
        ByteBuffer readOnly = buffer.asReadOnlyBuffer();

        //改变原缓冲区的内容
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }
        readOnly.position(0);
        readOnly.limit(buffer.capacity());

        while (readOnly.remaining() > 0) {
            System.out.println(readOnly.get());
        }
    }
}
