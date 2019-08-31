package com.wufeng;

import com.wufeng.registry.IRegistryCenter;
import com.wufeng.registry.RegisterCenterWithZK;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wangkai
 * @CreateTime 2019-06-16 22:53
 **/
@Component
public class GpRpcServer implements ApplicationContextAware, InitializingBean {
    //    ExecutorService executorService = Executors.newCachedThreadPool();
    private Map<String, Object> handlerMap = new HashMap();

    private int port;

    public GpRpcServer(int port) {
        this.port = port;
    }

    public IRegistryCenter registryCenter = new RegisterCenterWithZK();

    @Override
    public void afterPropertiesSet() {
        //接受客户端的链接
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //处理已经被接受的链接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().
                                    addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null))).
                                    addLast(new ObjectEncoder()).
                                    addLast(new ProcessorHandler(handlerMap));
                        }
                    });
            serverBootstrap.bind(port).sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                //拿到注解
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String serviceName = rpcService.value().getName();//拿到接口类定义
                String version = rpcService.version();//拿到版本号
                if (!StringUtils.isEmpty(version)) {
                    serviceName += "-" + version;
                }
                handlerMap.put(serviceName, serviceBean);
                registryCenter.registery(serviceName, getAddress());
            }
        }
    }

    /**
     * 获取本机IP
     *
     * @return
     */
    private String getAddress() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            System.out.println("本机的IP地址为" + inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddress.getHostAddress();
    }
}
