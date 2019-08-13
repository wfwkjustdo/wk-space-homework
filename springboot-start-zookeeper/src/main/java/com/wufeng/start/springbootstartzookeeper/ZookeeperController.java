package com.wufeng.start.springbootstartzookeeper;

import com.wufeng.starter.ZookeeperTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangkai
 * @Create 2019/8/13-19:52.
 * @Description
 */
@RestController
public class ZookeeperController {

    @Autowired
    ZookeeperTemplate zookeeperTemplate;

    @GetMapping("/createZK")
    public String creatZKNode() {
        try {
            zookeeperTemplate.createDate("/springZKNode","test");
        } catch (Exception e) {
            e.printStackTrace();
            return "create zknode error! ErrMsg:</br>"+e.getMessage();
        }
        return "create zknode success!";
    }
}
