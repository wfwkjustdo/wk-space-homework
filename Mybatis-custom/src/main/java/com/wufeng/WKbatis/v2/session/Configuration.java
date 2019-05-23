package com.wufeng.WKbatis.v2.session;

import com.wufeng.WKbatis.v2.TestMybatis;
import com.wufeng.WKbatis.v2.annotation.Entity;
import com.wufeng.WKbatis.v2.annotation.Select;
import com.wufeng.WKbatis.v2.binding.MapperRegistry;
import com.wufeng.WKbatis.v2.executor.CachingExecutor;
import com.wufeng.WKbatis.v2.executor.Executor;
import com.wufeng.WKbatis.v2.executor.SimpleExecutor;
import com.wufeng.WKbatis.v2.plugin.InterceptorChain;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 15:51
 * @Description
 **/
public class Configuration {
    public static final ResourceBundle sqlMappings;//SQL映射关系配置，使用注解时不用重复配置
    public static final ResourceBundle properties;//全局配置
    public static final MapperRegistry MAPPER_REGISTRY = new MapperRegistry();//维护接口和工厂类关系
    public static final Map<String, String> mappedStatements = new HashMap<String, String>();//维护接口方法与SQL关系

    private InterceptorChain interceptorChain = new InterceptorChain();//插件
    private List<Class<?>> mapperList = new ArrayList<>();//所有Mapper接口
    private List<String> classPaths = new ArrayList<>();//类所有文件

    static {
        sqlMappings = ResourceBundle.getBundle("sql");
        properties = ResourceBundle.getBundle("mybatis");
    }

    /**
     * 初始化时解析全局配置文件
     */
    public Configuration() {
        //Note：在properties和注解中重复配置SQL会覆盖
        //1.解析sql.properties
        for (String key : sqlMappings.keySet()) {
            Class mapper = null;
            String statement = null;
            String pojoStr = null;
            Class pojo = null;
            // properties中的value用--隔开，第一个是SQL语句
            statement = sqlMappings.getString(key).split("--")[0];
            // properties中的value用--隔开，第二个是需要转换的POJO类型
            pojoStr = sqlMappings.getString(key).split("--")[1];

            try {
                // properties中的key是接口类型+方法
                // 从接口类型+方法中截取接口类型
                mapper = Class.forName(key.substring(0, key.lastIndexOf(".")));
                pojo = Class.forName(pojoStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            MAPPER_REGISTRY.addMapper(mapper, pojo);//接口与放回的实体类关系
            mappedStatements.put(key, statement);//接口方法与SQL关系
        }

        //2.解析Mapper接口配置，扫描注册
        String mapperPath = properties.getString("mapper.path");
        scanPackage(mapperPath);
        for (Class<?> mapper : mapperList) {
            parsingClass(mapper);
        }

    }

    /**
     * 解析Mapper接口上配置的注解（SQL语句）
     * @param mapper
     */
    private void parsingClass(Class<?> mapper) {
        //1.解析类上的注解
        //如果有@Entity注解，说明时查询数据库的接口
        if (mapper.isAnnotationPresent(Entity.class)){
            for (Annotation annotation : mapper.getAnnotations()) {
                if (annotation.annotationType().equals(Entity.class)){
                    MAPPER_REGISTRY.addMapper(mapper,((Entity)annotation).value());
                }
            }
        }

        //2.解析方法上面的注解
        Method[] methods = mapper.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Select.class)){
                for (Annotation annotation: method.getDeclaredAnnotations()) {
                    if (annotation.annotationType().equals(Select.class)){
                        //注册接口类型+方法名和SQL语句的映射关系
                        String statement = method.getDeclaringClass().getName()+"."+method.getName();
                        mappedStatements.put(statement,((Select)annotation).value());
                    }
                }
            }
        }
    }

    /**
     * 根据全局配置文件的Mapper接口路径，扫描所有接口
     *
     * @param mapperPath
     */
    private void scanPackage(String mapperPath) {
        String classPath = TestMybatis.class.getResource("/").getPath();
        mapperPath = mapperPath.replace(".", File.separator);
        String mainPath = classPath + mapperPath;
        doPath(new File(mainPath));
        for (String className : classPaths) {
            //在windows电脑上面的语句
            className = className.replace(classPath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
            /* //在mac电脑上面的语句
            className = className.replace(classPath, "")
                    .replace("/", ".").replace(".class", "");
            * */
            Class<?> clazz = null;

            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clazz.isInterface()) {
                mapperList.add(clazz);
            }
        }
    }

    /**
     * 获取文件或文件夹下所有的类.class
     *
     * @param file
     */
    private void doPath(File file) {
        //文件夹，遍历
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {
            //文件，直接添加
            if (file.getName().endsWith(".class")) {
                classPaths.add(file.getPath());
            }
        }
    }

    /**
     * 创建执行器，当开启缓存时使用缓存修饰
     * 当配置插件时，使用插件代理
     * @return
     */
    public Executor newExecutor() {
        Executor executor = null;
        if (properties.getString("cache.enabled").equals("true")){
            executor = new CachingExecutor(new SimpleExecutor());
        }else{
            executor = new SimpleExecutor();
        }

        //目前只拦截了Executor，所有的插件都对Executor进行代理，没有对拦截类和方法签名进行判断
        if (interceptorChain.hasPlugin()){

        }

        return executor;
    }

    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        return MAPPER_REGISTRY.getMapper(clazz,sqlSession);
    }

    /**
     * 根据statement ID获取SQL
     * @param id
     * @return
     */
    public String getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    /**
     * 根据statement判断是否存在映射的SQL
     * @param statementName
     * @return
     */
    public boolean hasStatement(String statementName) {
        return mappedStatements.containsKey(statementName);
    }
}
