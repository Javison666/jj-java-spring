package cn.jiweiqing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by Weiqing Ji on 2019/11/7.
 */
@Controller
public class AuthController {
    @RequestMapping("/index")
    public  String index(){
        return "index";
    }
}
