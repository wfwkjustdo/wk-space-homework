package com.wufeng.orm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author wangkai
 * @Create 2019/4/22-15:48.
 */
@Data
@Table(name = "t_employee")
@Entity
public class Employee {
    private Long id;
    private String name;
    private String addr;
    private Integer age;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + addr + '\'' +
                ", age=" + age +
                '}';
    }
}
