package com.jiangwg.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by JiangWeiGen on 2017/12/29 0029.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void defaultErrorHandler(Exception e) {
        System.out.println("抓到异常了：" + e);

    }
}
