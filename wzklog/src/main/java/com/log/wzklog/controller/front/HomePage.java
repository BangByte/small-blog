package com.log.wzklog.controller.front;


import com.log.wzklog.Service.LoginService;
import com.log.wzklog.ServiceImpl.LoginServiceImpl;
import com.log.wzklog.bo.SaveSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomePage {
    public HomePage(){}
    private LoginService loginService;
    public static Integer REQUEST_COUNT = 0;

    @Autowired
    public void HomePage(LoginServiceImpl loginService){
        this.loginService = loginService;
    }
    @GetMapping("/front")
    public ModelAndView FroutHome(){
        ModelAndView mv = new ModelAndView();
        List<SaveSend> list = loginService.byCount();
//        for(SaveSend s : list){
//            System.out.println(s.toString());
//        }
        REQUEST_COUNT++;
        mv.addObject("list",list);
        mv.addObject("count",list.size());
        mv.setViewName("production/front/txtAll");
        return mv;
    }

    @GetMapping("/read/{id}")
    public ModelAndView ReadHome(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        SaveSend saveSend = loginService.byCountId(id);

        REQUEST_COUNT++;
        mv.addObject("saveSend",saveSend);
        mv.setViewName("production/front/read");
        return mv;
    }
}
