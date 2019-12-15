package com.log.wzklog.controller;

import cn.hutool.core.util.RandomUtil;
import com.log.wzklog.ServiceImpl.LoginServiceImpl;
import com.log.wzklog.bo.SaveSend;
import com.log.wzklog.config.ApplicationStart;
import com.log.wzklog.controller.front.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/page")
public class PageDistribute {
    public PageDistribute(){}
    Logger logger = LoggerFactory.getLogger(PageDistribute.class);
    private LoginServiceImpl loginService;
    private SaveSend saveSend;
    @Autowired
    public PageDistribute(LoginServiceImpl loginService, SaveSend saveSend){
        this.loginService = loginService;
        this.saveSend = saveSend;
    }

    @GetMapping("/form")
    public String home(){
        return "production/form";
    }

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        LocalDateTime startTime = ApplicationStart.startTime;
        LocalDateTime endTime = LocalDateTime.now();

        Duration duration = Duration.between(startTime,endTime);
        long minute = duration.toMinutes();
        logger.info("[ApplicationTime]："+minute+" min");
        mv.addObject("REQUEST_COUNT",HomePage.REQUEST_COUNT);
        mv.addObject("minute",minute);
        mv.setViewName("production/index");
        return mv;
    }


    @PostMapping("/send")
    public String saveContex(HttpServletRequest request){
        Integer numId = Integer.parseInt(RandomUtil.randomNumbers(7));
        DateTimeFormatter str = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String flag = request.getParameter("flag");

        LocalDateTime saveTime = LocalDateTime.now();
        String strTime = str.format(saveTime);

        saveSend.setSaveSend(numId,title,content,strTime,flag);
        Integer integer = loginService.saveContent(saveSend);


        System.out.println(title+"  "+content);
        return "production/form";
    }
}
