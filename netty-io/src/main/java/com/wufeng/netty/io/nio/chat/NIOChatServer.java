package com.wufeng.netty.io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author wangkai
 * @Create 2019/7/3-20:01.
 * @Description 网络多客户端聊天室
 * 功能1： 客户端通过Java NIO连接到服务端，支持多客户端的连接
 * 功能2：客户端初次连接时，服务端提示输入昵称，如果昵称已经有人使用，提示重新输入，如果昵称唯一，则登录成功，之后发送消息都需要按照规定格式带着昵称发送消息
 * 功能3：客户端登录后，发送已经设置好的欢迎信息和在线人数给客户端，并且通知其他客户端该客户端上线
 * 功能4：服务器收到已登录客户端输入内容，转发至其他登录客户端。
 */
public class NIOChatServer {
    private int port = 8080;
    private Charset charset = Charset.forName("UTF-8");

    //用来记录在线人数，以及昵称
    private static HashSet<String> users = new HashSet<String>();

    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    //相当于自定义协议格式，与客户端协商好
    private static String USER_CONTENT_SPILIT = "#@#";

    private Selector selector = null;

    public NIOChatServer(int port) throws IOException {
        this.port = port;
        ServerSocketChannel server = ServerSocketChannel.open();

        server.bind(new InetSocketAddress(this.port));
        server.configureBlocking(false);
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务已经启动，监听端口是：" + this.port);
    }


    public void listen() throws IOException {
        while (true){
            int wait = selector.select();
            if (wait==0){
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();//可以通过这个方法，知道可用通道的集合
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();
                process(key);
            }
        }
    }


    private void process(SelectionKey key) {

    }
}
