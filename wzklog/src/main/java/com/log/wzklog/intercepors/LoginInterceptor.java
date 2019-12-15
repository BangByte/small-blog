package com.log.wzklog.intercepors;

import com.log.wzklog.bo.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer urlBuffer = request.getRequestURL();
        String url = String.valueOf(urlBuffer).substring(0,21);
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user != null){
                return true;
        }else{
            response.sendRedirect(url+"/login");
            return false;
        }

    }
}
