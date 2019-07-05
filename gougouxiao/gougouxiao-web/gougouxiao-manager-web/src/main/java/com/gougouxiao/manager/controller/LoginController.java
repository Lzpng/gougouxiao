package com.gougouxiao.manager.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**登录控制器*/

@Controller
@RequestMapping("/user")
public class LoginController {

    /**用户登录*/
    @PostMapping("/login")
    public String login(String username,String password){
        try {
            //获取认证的主体
            Subject subject = SecurityUtils.getSubject();
            //创建用户名与密码令牌
            UsernamePasswordToken upt = new UsernamePasswordToken(username,password);
            //身份认证
            subject.login(upt);
            //判断是否通过认证
            if (subject.isAuthenticated()){
                return "redirect:/admin/index.html";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/login.html";
    }

    /**获取登录用户名*/
    @PostMapping("/findLoginName")
    @ResponseBody
    public Map<String,String> findLoginName(){
        //获取登录名，因为Subject底层有默认的存储域，所有直接从底层存储域中获取
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("username = " + username);
        Map<String,String> map = new HashMap<>();
        map.put("username", username);
        return map;
    }
}
