package com.wufeng.rpc.discovery;

/**
 * @Author wangkai
 * @CreateTime 2019-08-31 16:59
 * @Description
 **/
public interface IServiceDiscovery {

    /**
     * 根据服务名称返回服务地址
     * @param serviceName
     * @return
     */
    String discovery(String serviceName);
}
