package com.jiangwg.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JiangWeiGen on 2018/1/9 0009.
 */
@Controller
public class TemplateController {
    /**
     * 返回html模板.
     */
/*    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String,Object> map){
        map.put("hello22","from TemplateController.helloHtml");
        return "hello";
    }

    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("hello","from TemplateController.helloHtml");
        return "hello";
    }*/

    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String,Object> map){
        map.put("hello","hello jsp");
        return "hello";
    }
}
