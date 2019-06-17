package com.wufeng;

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
public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Map<String, Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            //输入流中应该有什么东西？
            //请求那个类、方法名称、参数
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest);//反射调用本地服务

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
        if (args != null) {
            Class<?>[] types = new Class[args.length];//获取每个参数的类型
            for (int i = 0; i < types.length; i++) {
                types[i] = args[i].getClass();
            }
            Class clazz = Class.forName(request.getClassName());//根据请求的类进行加载
            method = clazz.getMethod(request.getMethodName(), types);//sayHello, saveUser找到这个类中的方法
        } else {
            Class clazz = Class.forName(request.getClassName());//根据请求的类进行加载
            method = clazz.getMethod(request.getMethodName());//sayHello, saveUser找到这个类中的方法
        }
        Object result = method.invoke(service, args);//HelloServiceImpl 进行反射调用
        return result;
    }
}
