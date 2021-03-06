package com.wufeng.WKbatis.v2.executor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 18:46
 * @Description 结果集处理
 **/
public class ResultSetHandler {
    public <T> T handle(ResultSet resultSet, Class type) {
        //直接调用Class的方法产生一个实例
        Object pojo = null;
        try {
            pojo = type.newInstance();
            //遍历结果集
            if (resultSet.next()) {
                for (Field field : pojo.getClass().getDeclaredFields()) {
                    setValue(pojo, field, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) pojo;
    }

    /**
     * 通过反射给属性赋值
     *
     * @param pojo
     * @param field
     * @param rs
     */
    private void setValue(Object pojo, Field field, ResultSet rs) {
        try {
            Method setMethod = pojo.getClass().getMethod("set" + firstWordCapital(field.getName()), field.getType());
            setMethod.invoke(pojo, getResult(rs, field));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据发射判断类型，从ResultSet中区对应类型参数
     *
     * @param rs
     * @param field
     * @return
     */
    private Object getResult(ResultSet rs, Field field) throws SQLException {
        Class type = field.getType();
        String dataName = HumpToUnderline(field.getName());//驼峰转下划线
        if (Integer.class == type) {
            return rs.getInt(dataName);
        } else if (String.class == type) {
            return rs.getString(dataName);
        } else if (Long.class == type) {
            return rs.getLong(dataName);
        } else if (Boolean.class == type) {
            return rs.getBoolean(dataName);
        } else if (Double.class == type) {
            return rs.getDouble(dataName);
        } else {
            return rs.getString(dataName);
        }

    }

    /**
     * 单词首字母大写
     *
     * @param word
     * @return
     */
    private String firstWordCapital(String word) {
        String first = word.substring(0, 1);
        String tail = word.substring(1);
        return first.toUpperCase() + tail;
    }

    /**
     * Java驼峰命名转数据库下划线
     *
     * @param para
     * @return
     */
    public static String HumpToUnderline(String para) {
        StringBuffer sb = new StringBuffer(para);
        int temp = 0;
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }


}
