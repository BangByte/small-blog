package com.log.wzklog.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.log.wzklog.Service.LoginService;
import com.log.wzklog.ServiceImpl.LoginServiceImpl;
import com.log.wzklog.bo.UserInfo;
import com.log.wzklog.enumutil.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 创建用户功能
 */
@Controller
public class CreateUserPage {
    public CreateUserPage(){}

    private UserInfo userInfo;
    private LoginService loginService;
    @Autowired
    public CreateUserPage(UserInfo userInfo, LoginServiceImpl loginServiceImpl){
        this.userInfo = userInfo;
        this.loginService = loginServiceImpl;
    }

    @PostMapping("/createUser")
    public String createUser(HttpServletRequest request, HttpSession httpSession){
        String cUsername = request.getParameter("createuser");
        String cPhone = request.getParameter("createphone");
        String cPassword = request.getParameter("createpassword");
        //  存储 重复 信息内容
        String strNameInfo = null;
        String strPhoneInfo = null;
        //System.out.println(cUsername+cEmail+cPassword);

        List<UserInfo> i = loginService.byNameOrPhone(cUsername,cPhone);
        if(i.size()>0){
            // 有重复用户注册不跳转
            for(UserInfo u : i){
                if(u.getUserName().equals(cUsername)){
                    System.out.println("用户名已注册");
                    strNameInfo = "用户名已注册";
                    httpSession.setAttribute("nameinfo",strNameInfo);
                }
                if(u.getPhone().equals(cPhone)){
                    System.out.println("phone已注册");
                    strPhoneInfo = "phone已注册";
                    httpSession.setAttribute("phoneinfo",strPhoneInfo);
                }
             //   System.out.println(u);
            }
            if(strNameInfo != null || strPhoneInfo != null){
                return "redirect:login#signup";
            }
            httpSession.setMaxInactiveInterval(1*4);
        }else{
            // 跳转到登录
            //保存 创建
            Integer strId = saveUser(userInfo,cUsername,cPassword,cPhone);
            // 用于 请登录
            userInfo.setUserInfo(null,null,null,null, LoginEnum.SUCCESS.getValue());
            httpSession.setAttribute("user",userInfo);
            httpSession.setMaxInactiveInterval(1*4);
            return "production/login";
        }

        System.out.println(userInfo);
        return "production/login";
    }


    public Integer saveUser(UserInfo userInfo,String username,String password,String phone){
        Integer id = null;
        // id随机生成
        Integer numId = Integer.parseInt(RandomUtil.randomNumbers(6));

        // 防止 网页刷新 重复 保存数据
        if(loginService.byUserLogin(username,password).getUserName() == null){
            userInfo.setUserInfo(username,password,numId,phone,null);
            id = loginService.saveUser(userInfo);
            return id;
        }else{
            return id;
        }
    }
}
