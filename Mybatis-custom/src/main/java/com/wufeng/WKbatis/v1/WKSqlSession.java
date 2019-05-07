package com.wufeng.WKbatis.v1;

/**
 * @Author wangkai
 * @CreateTime 2019/5/7 23:07
 **/
public class WKSqlSession {
    private WKConfiguration configuration;
    private WKExecutor executor;

    public WKSqlSession(WKConfiguration configuration, WKExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz,this);
    }

    public <T> T selectOne(String statementId, Object arg){
        return null;
    }
}
