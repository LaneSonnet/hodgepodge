package com.jiangwg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by JiangWeiGen on 2017/12/29 0029.
 */
@SpringBootApplication
@MapperScan("com.jiangwg.dao")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}