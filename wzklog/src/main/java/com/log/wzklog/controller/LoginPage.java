package com.log.wzklog.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.log.wzklog.Service.LoginService;
import com.log.wzklog.ServiceImpl.LoginServiceImpl;
import com.log.wzklog.bo.UserInfo;
import com.log.wzklog.enumutil.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


/**
 * 登录功能
 */
@Controller
public class LoginPage {
    public LoginPage(){}

    private UserInfo userInfo;
    private LoginService loginService;
    @Autowired
    public LoginPage(UserInfo userInfo, LoginServiceImpl loginServiceImpl){
        this.userInfo = userInfo;
        this.loginService = loginServiceImpl;
    }

    //登录页面转发
    @GetMapping("/login")
    public String loginPage(){
        return "production/login";
    }


    //用户密码登录
    @PostMapping("/userLogin")
    public String userLogin(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response){
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo userInfo = loginService.byUserLogin(userName,password);
        System.out.println(userInfo);
        httpSession.setAttribute("user",userInfo);
        if(userInfo != null){
            if(userInfo.getFlag().equals("success")){
                return "redirect:/page/index";
            }else{
                return "production/login";
            }
        }else{
            return "production/login";
        }
    }

    // 仪表跳转
    @GetMapping("/home")
    public String home(){
        return "production/index";
    }

}
