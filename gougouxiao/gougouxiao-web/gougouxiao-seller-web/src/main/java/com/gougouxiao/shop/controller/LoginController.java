package com.gougouxiao.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.service.SellerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**获取商家用户登录信息*/
@Controller
public class LoginController {

    @Reference(timeout = 10000)
    private SellerService sellerService;

    @PostMapping("/login")
    public String login(String username,String password){

        try {
            /*########### 让shiro框架帮我们作身份认证  ##########*/
            /* 获取HttpSession相关的主体 */
            Subject subject = SecurityUtils.getSubject();
            //创建用户与密码的令牌
            UsernamePasswordToken upt = new UsernamePasswordToken(username,password);
            //身份认证
            subject.login(upt);
            //判断用户是否认证
            if (subject.isAuthenticated()){
                return "redirect:/admin/index.html";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/shoplogin.html";
    }

    /**获取登录用户名*/
    @PostMapping("/findLoginName")
    @ResponseBody
    public Map<String,String> findByLoginName(){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        Map<String,String> map = new HashMap<>();
        System.out.println("loginName = " + loginName);
        map.put("loginName", loginName);
        return map;
    }
}
