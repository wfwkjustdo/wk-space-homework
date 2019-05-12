package com.wufeng.WKbatis.v2.parameter;

import java.sql.PreparedStatement;

/**
 * @Author wangkai
 * @CreateTime 2019/5/12 15:14
 * @Description 参数处理器
 **/
public class ParameterHandler {
    private PreparedStatement psmt;

    public ParameterHandler(PreparedStatement statement) {
        this.psmt = statement;
    }

    public void setParameter(Object[] parameters) {
        try {
            //ParameterStatement的序号是从1开始的
            for (int i = 0; i < parameters.length; i++) {
                int k = i + 1;
                if (parameters[i] instanceof Integer) {
                    psmt.setInt(k, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Long) {
                    psmt.setLong(k, (Long) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    psmt.setString(k, String.valueOf(parameters[i]));
                } else if (parameters[i] instanceof Boolean) {
                    psmt.setBoolean(k, (Boolean) parameters[i]);
                } else {
                    psmt.setString(k, String.valueOf(parameters[i]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
