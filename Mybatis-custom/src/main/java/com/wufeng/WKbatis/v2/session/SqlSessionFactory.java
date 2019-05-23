package com.wufeng.WKbatis.v2.session;


/**
 * @Author wangkai
 * @CreateTime 2019/5/11 15:45
 * @Description 会话工厂类，用于解析配置文件，产生SqlSession
 **/
public class SqlSessionFactory {
    private Configuration configuration;

    /**
     * build方法用户初始化Configuration，解析配置文件的工作在Configuration的构造函数中
     * @return
     */
    public SqlSessionFactory build(){
        configuration = new Configuration();
        return this;
    }

    /**
     * 获取DefaultSqlSession
     * @return
     */
    public DefaultSqlSession openSqlSession(){
        return new DefaultSqlSession(configuration);
    }
}
