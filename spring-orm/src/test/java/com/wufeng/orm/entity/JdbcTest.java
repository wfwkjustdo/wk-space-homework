package com.wufeng.orm.entity;

import javafx.collections.ObservableList;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangkai
 * @Create 2019/4/22-16:04.
 */
public class JdbcTest {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("wufeng");
//        employee.setAddress("shenzhen");
//        employee.setAge(18);

        List<?> result = select(employee);
        System.out.println(Arrays.toString(result.toArray()));
    }

    private static List<?> select(Object condition) {
        try {
            Class<?> entityClass = condition.getClass();
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.建立连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gp-vip-spring-db-2019?characterEncoding=UTF-8&rewriteBatchedStatements=true", "root", "123456");
            Table table = entityClass.getAnnotation(Table.class);
            //3.创建语句开始事务
            String sql = "select * from " + table.name();
            StringBuffer where = new StringBuffer(" where 1=1 ");
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(condition);
                if (value != null) {
                    Class<?> clazz = field.getType();
                    if (String.class == clazz) {
                        where.append(" and " + field.getName() + " = '" + value + "'");
                    } else {
                        where.append(" and " + field.getName() + " = " + value);
                    }
                }
            }
            System.out.println("查询SQL为："+sql + where.toString());
            PreparedStatement pstmt = conn.prepareStatement(sql + where.toString());
            //4.执行语句集
            ResultSet rs = pstmt.executeQuery();
            //5.获取结果集
            List<Object> result = new ArrayList<Object>();
            result.add(handleResultSet(rs,condition));
            //6.关闭结果集、关闭语句集、关闭连接
            rs.close();
            pstmt.close();
            conn.close();

            return  result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    /**
     * 处理结果集
     * @param rs
     * @param condition
     * @return
     */
    private static List<Object> handleResultSet(ResultSet rs, Object condition) throws Exception {
        List<Object> result = new ArrayList<Object>();
        Class<?> entityClass = condition.getClass();
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()){
            //===========Begin ORM ============
            Object instance = entityClass.newInstance();
            for (int i = 1; i < columnCount; i++) {
                String columnName = rs.getMetaData().getColumnName(i);
                Field field = entityClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(instance,rs.getObject(columnName));
            }
            //===========End ORM ==============
            result.add(instance);
        }
        return result;
    }
}
