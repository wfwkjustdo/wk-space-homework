package com.wufeng.starter;

import com.wufeng.starter.autoconfiguration.ZookeeperProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Author wangkai
 * @Create 2019/8/13-18:55.
 * @Description
 */
public class ZookeeperTemplate {
    private ZookeeperProperties zookeeperProperties;
    private static CuratorFramework curatorFramework =null;

    public ZookeeperTemplate(ZookeeperProperties zookeeperProperties) {
        this.zookeeperProperties = zookeeperProperties;
    }

    public void createDate(String path,String data) throws Exception {
        if (null == curatorFramework){
            initCuratorFramework();
        }

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath(path,data.getBytes());
    }

    private void initCuratorFramework() {
        synchronized (ZookeeperTemplate.class){
            curatorFramework = CuratorFrameworkFactory.builder()
                    .connectString(zookeeperProperties.getConnect_str())
                    .sessionTimeoutMs(5000)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .namespace(zookeeperProperties.getNameSpace())
                    .build();
            curatorFramework.start();
        }
    }
}
