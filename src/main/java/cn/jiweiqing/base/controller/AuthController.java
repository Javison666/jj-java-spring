package cn.jiweiqing.base.controller;

import cn.jiweiqing.base.bean.ResponseUtils;
import cn.jiweiqing.base.bean.ResultBean;
import cn.jiweiqing.base.bean.UserBean;
import cn.jiweiqing.base.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Weiqing Ji on 2019/11/7.
 */
@RestController
@Controller
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public ResultBean login(String username, String password, HttpSession session) {
        String errorMsg;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseUtils.response(true, "success", null);
        } catch (AuthenticationException e) {
            token.clear();
            errorMsg = "用户或密码不正确";
            logger.error("登录错误【账号(" + username + ")或密码(" + password + ")错误】", e);
        } catch (Exception e) {
            token.clear();
            errorMsg = "登录异常！请联系管理员！";
            logger.error("登录错误【未知错误】", e);
        }
        return ResponseUtils.error(400, "账号和密码错误");
    }

    /**
     * user logout
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/logout")
    public ResultBean logout(HttpSession session) {
        SecurityUtils.getSubject().logout();
        return ResponseUtils.response(true, "success", null);
    }

    @RequestMapping(value = "/api/error")
    public String error() {
        return "400";
    }
}
