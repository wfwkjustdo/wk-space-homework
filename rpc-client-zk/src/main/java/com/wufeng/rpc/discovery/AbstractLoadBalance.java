package com.wufeng.rpc.discovery;

import java.util.List;

/**
 * @Author wangkai
 * @CreateTime 2019-08-31 18:19
 * @Description
 **/
public abstract class AbstractLoadBalance implements LoadBalanceStrategy{

    /**
     * repos可能为空，可能只有一个。
     * @param repos
     * @return
     */
    @Override
    public String selecthost(List<String> repos) {
        if (repos==null||repos.size()==0){
            return null;
        }
        if (repos.size()==1){
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);
}
