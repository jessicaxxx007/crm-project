package com.yjx.controller;

import com.yjx.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
    //跳转首页
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    //系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }
    //跳转到后端管理主页
    @RequestMapping("main")
    public String main(){
        return "main";
    }

}