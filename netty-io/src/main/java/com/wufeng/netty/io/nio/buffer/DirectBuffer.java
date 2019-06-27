package com.wufeng.netty.io.nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wangkai
 * @Create 2019/6/27-20:04.
 * @Description 直接缓冲区
 * Zero copy减少了一个拷贝的过程
 */
public class DirectBuffer {
    public static void main(String[] args) throws Exception {
        //在Java里面存储的只是缓冲区的应用地址
        //管理效率

        //首先我们从磁盘上读取刚才我们写出的文件内容
        String inFile = "E://test.txt";
        FileInputStream fin = new FileInputStream(inFile);
        FileChannel fcin = fin.getChannel();

        //把刚才读取的内容写入到一个新的文件中
        String outFile = String.format("E://testcopy.txt");
        FileOutputStream fout = new FileOutputStream(outFile);
        FileChannel fcout = fout.getChannel();

        //使用allccationDirect,而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }
    }
}
