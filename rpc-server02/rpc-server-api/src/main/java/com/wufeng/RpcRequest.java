package com.wufeng;

/**
 * @Author wangkai
 * @CreateTime 2019-06-16 22:43
 **/
public class RpcRequest {

    private String className;
    private String methodName;
    private Object[] patameters;

    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getPatameters() {
        return patameters;
    }

    public void setPatameters(Object[] patameters) {
        this.patameters = patameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
