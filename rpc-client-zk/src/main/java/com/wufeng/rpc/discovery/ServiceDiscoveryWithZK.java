package com.wufeng.rpc.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.util.backoff.ExponentialBackOff;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangkai
 * @CreateTime 2019-08-31 17:09
 * @Description
 **/
public class ServiceDiscoveryWithZK implements IServiceDiscovery {
    CuratorFramework curatorFramework =null;
    List<String> serviceRepos = new ArrayList<>();//服务地址的本地缓存

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZKConfig.CONNECTION_STR)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("registry")
                .build();
        curatorFramework.start();
    }

    /**
     * 服务的查找
     * 设置监听
     *
     * @param serviceName
     * @return
     */
    @Override
    public String discovery(String serviceName) {
        //完成了服务的地址查找（服务地址被删除）
        String path = "/"+serviceName;
        if (serviceName.isEmpty()){
            try {
                serviceRepos = curatorFramework.getChildren().forPath(path);
                registryWatch(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void registryWatch(final String path) throws Exception {
        PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener nodeCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework1, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("客户端收到节点变更的时间");
                serviceRepos = curatorFramework1.getChildren().forPath(path);//再次更新本地的缓存地址
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
}
