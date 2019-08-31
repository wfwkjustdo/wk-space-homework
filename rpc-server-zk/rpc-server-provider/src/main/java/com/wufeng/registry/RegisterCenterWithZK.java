package com.wufeng.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Author wangkai
 * @CreateTime 2019-08-24 23:17
 * @Description
 **/
public class RegisterCenterWithZK implements IRegistryCenter {
    CuratorFramework curatorFramework =null;
    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZKConfig.CONNECTTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                namespace("registry").build();
        curatorFramework.start();
    }

    @Override
    public void registery(String serviceName, String serviceAddress) {
        String servicePath = "/" + serviceName;
        try{
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath);
            }
            String addressPath = servicePath+"/"+serviceAddress;
            curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath);
            System.out.println("服务注册成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
