package cn.jiweiqing.base.controller;

import cn.jiweiqing.base.bean.UserBean;
import cn.jiweiqing.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Weiqing Ji on 2019/11/7.
 */
@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public  String index(){
        return "index";
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public String login(String username, String password){
        UserBean userBean = userService.loginIn(username,password);
        if(userBean!=null){
            return  "success";
        }else{
            return  "error";
        }
    }
}
