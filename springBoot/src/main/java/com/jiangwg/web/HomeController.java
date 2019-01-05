package com.jiangwg.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JiangWeiGen on 2017/12/29 0029.
 */
@RestController
public class HomeController {

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
}
