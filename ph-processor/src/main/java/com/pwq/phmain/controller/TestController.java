package com.pwq.phmain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：WenqiangPu
 * @Description
 * @Date：Created in 17:14 2018/1/11
 * @Modified By：
 */
@RestController
@RequestMapping("/spider")
public class TestController {
    @RequestMapping("/")
    public String test(){
        System.out.println("ok");
        return "sucess";
    }

}
