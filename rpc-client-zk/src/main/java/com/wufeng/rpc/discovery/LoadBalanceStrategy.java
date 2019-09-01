package com.wufeng.rpc.discovery;

import java.util.List;

/**
 * @Author wangkai
 * @CreateTime 2019-08-31 18:18
 * @Description
 **/
public interface LoadBalanceStrategy {
    String selecthost(List<String> repos);
}
