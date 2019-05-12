package com.wufeng.WKbatis.v2;

import com.wufeng.WKbatis.v2.executor.ResultSetHandler;

/**
 * @Author wangkai
 * @CreateTime 2019/5/11 17:33
 * @Description
 **/
public class TestMybatis {

    public static void main(String[] args) {
        String temp  = "fParentNoLeader";
        String temp1 = ResultSetHandler.HumpToUnderline(temp);
        System.out.println(temp1);
    }
}
