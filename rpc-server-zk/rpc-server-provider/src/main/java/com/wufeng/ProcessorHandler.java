package com.wufeng;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Author wangkai
 * @Create 2019/6/17-10:54.
 * @Description
 */
public class ProcessorHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private Map<String, Object> handlerMap;

    public ProcessorHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
        Object result = invoke(rpcRequest);
        channelHandlerContext.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String serviceName = request.getClassName();
        String version = request.getVersion();
        //增加版本号
        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }

        Object service = handlerMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("service not found:" + serviceName);
        }
        Object[] args = request.getParameters();//拿到客户端请求的参数
        Method method = null;
        Class clazz = Class.forName(request.getClassName());//根据请求的类进行加载
        method = clazz.getMethod(request.getMethodName(), request.getParamTypes());//sayHello, saveUser找到这个类中的方法
        Object result = method.invoke(service, args);//HelloServiceImpl 进行反射调用
        return result;
    }


}
