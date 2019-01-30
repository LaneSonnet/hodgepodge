package com.jiangwg.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangwg.dao.UserDao;
import com.jiangwg.po.RandomPro;
import com.jiangwg.po.UserPo;

/**
 * Created by JiangWeiGen on 2017/12/29 0029.
 */
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Resource
    private UserDao userDao;

    @Resource
    private RandomPro randomPro;

/*    @RequestMapping("/hello")
    public String hello(){
        return "hyikujoio";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "hyikujoio22sdfghjk";
    }*/

    @RequestMapping("/getCustomer")
    public Customer getCustomer(@RequestBody Customer customer) throws Exception {
        System.out.println(customer);
        Customer customer1 = new Customer();
        customer1.setId(22);
        customer1.setName("张三");
        customer1.setSex("男");
        /*if (true) {
            throw new Exception("访问出错");
        }*/
        return customer1;
    }

    @RequestMapping("/getUser")
    public List<UserPo> getUser(@RequestBody String name) throws Exception {
        logger.info("请求入参【{}】", name);
        logger.info("随机值【{}】", randomPro.toString());
/*        if (true) {
            throw new Runtime


            Exception("getuser exception嗷嗷嗷啊");
        }*/

        return userDao.selectByName("%" + name + "%");
    }
}
