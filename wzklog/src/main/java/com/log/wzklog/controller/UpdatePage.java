package com.log.wzklog.controller;

import com.log.wzklog.Service.LoginService;
import com.log.wzklog.ServiceImpl.LoginServiceImpl;
import com.log.wzklog.bo.SaveSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UpdatePage {
    public UpdatePage(){}
    private LoginService loginService;
    @Autowired
    public void UpdatePage(LoginServiceImpl loginService){
        this.loginService = loginService;
    }

    @GetMapping("/tables")
    public ModelAndView tables(){
        ModelAndView mv = new ModelAndView();
        List<SaveSend> list = loginService.byCount();
        mv.addObject("list",list);
        mv.setViewName("production/tables");
        return mv;
    }

    @GetMapping("/del/{id}")
    public ModelAndView deleteContent(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/tables");
        Integer num = loginService.delTextId(id);
        System.out.println(num);
        return mv;
    }

    @GetMapping("/upd/{id}")
    public ModelAndView updateContent(@PathVariable("id") Integer id, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("forward:/tables");
        SaveSend saveSend = loginService.byCountId(id);
        mv.addObject("saveSend",saveSend);
        return mv;
    }
    @PostMapping("/sendUp/{id}")
    public ModelAndView sendUpdate(@PathVariable("id") Integer id, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("forward:/tabless");
        String title = request.getParameter("title");
        String flag = request.getParameter("flag");
        loginService.upTextId(id,title,flag);
        System.out.println(id);
        System.out.println(request.getParameter("title"));
        return mv;
    }
}
