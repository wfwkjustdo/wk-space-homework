package com.wufeng;

import java.io.Serializable;

/**
 * @Author wangkai
 * @CreateTime 2019-06-16 22:43
 **/
public class RpcRequest implements Serializable {

    private String className;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

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

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
