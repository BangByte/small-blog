package com.log.wzklog.controller;

import cn.hutool.json.JSONObject;
import com.github.jackieonway.sms.entity.TencentSmsRequest;
import com.github.jackieonway.sms.service.SmsService;
import com.log.wzklog.Service.LoginService;
import com.log.wzklog.bo.LoginStatus;
import com.log.wzklog.bo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class ForgetCipherPage {
    public ForgetCipherPage(){}

    @Autowired
    private SmsService tencentSmsService;
    @Autowired
    private LoginStatus loginStatus;
    @Autowired
    private LoginService loginService;

    String phone = null;
    String note = "6666";
    String password = null;

    @PostMapping(value = "/forget",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String forgetCipher(HttpServletRequest request) {
        String newCipher = request.getParameter("newcipher");
        String newOkCipher = request.getParameter("newOkcipher");
        String phone = request.getParameter("phone");

        if(newCipher != null && newOkCipher != null){
            if(newCipher.equals(newOkCipher)){
                if(newCipher == "" || newOkCipher == ""){
                    loginStatus.setNewPW(false);
                    return "{\"kong\":true}";
                }
                this.password = newOkCipher;
                loginStatus.setNewPW(true);
                return "{\"success\":true}";
            }else{
                loginStatus.setNewPW(false);
                return "{\"success\":false}";
            }
        }else{
            loginStatus.setNewPW(false);
            return "{\"kong\":false}";
        }

    }

    @PostMapping(value = "/phone",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String phoneCheck(HttpServletRequest request){
        phone = request.getParameter("phone");
        String regex = "^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|" +
                "([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        if(phone != null){
            Matcher matcher = Pattern.compile(regex).matcher(phone);
            boolean isPhone = matcher.matches();
            if(isPhone){
                UserInfo userInfo = loginService.byPhone(phone);
                if(userInfo != null){
                    loginStatus.setCheck(true);
                    loginStatus.setLogon(true);
                    // note() 发送短信
                    return "{\"isphone\":true,\"logon\":true}";
                }else{
                    return "{\"isphone\":true,\"logon\":false}";
                }
            }else{
                loginStatus.setCheck(false);
                return "{\"isphone\":false}";
            }
        }else{
            loginStatus.setCheck(false);
            return "{\"isnull\":true}";
        }
    }

    public Object note(){
        String[] paramst = {"520","1314"};
        TencentSmsRequest tencentSmsRequest = new TencentSmsRequest();
        tencentSmsRequest.setPhoneNumber(new String[]{"17839353692"});
        tencentSmsRequest.setParams(paramst);
        return tencentSmsService.sendTemplateSms("377524", tencentSmsRequest);
    }

    @PostMapping(value = "/noteCheck",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String noteCheck(HttpServletRequest request){
        String phones = request.getParameter("phone");
        String notes = request.getParameter("note");

        if(phone != null && note != null){
            if(phone.equals(phones) && note.equals(notes)){
                loginStatus.setOk(true);
                JSONObject jsonObj = new JSONObject(loginStatus);
                System.out.println(jsonObj.toString());
                if(loginStatus.isLogon() && loginStatus.isNewPW()){
                    loginService.upPassword(phone,password);
                    phone = null;
                    password = null;
                }
                System.out.println(jsonObj.toString());
                return jsonObj.toString();//{"isOk":true,"newPW":false,"check":true,"logon":true}
            }else{
                return "{\"isOk\":false}";
            }
        }else{
            return "{\"isOk\":false}";
        }
    }
}
