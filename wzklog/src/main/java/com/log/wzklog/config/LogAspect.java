package com.log.wzklog.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final String STR = "execution(* com.log.wzklog.DaoImpl.LoginDaoImpl..*(..))";

    @Before(STR)
    public void doBefore(JoinPoint jp){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("[Before] Request_URL："+request.getRequestURL().toString());
        logger.info("[Before] Request_Method："+request.getMethod());
        logger.info("[Before] Request_ip："+request.getRemoteAddr());
        logger.info("[Before] Dao_Method："+jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName());
        logger.info("[Before] Method_Param："+ Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = STR,returning = "result")
    public void doAferReturning(Object result){
        logger.info("[AfterReturn] Method_Result："+result);
    }

    @AfterThrowing(value = STR,throwing = "exception")
    public void doAfterThrowing(JoinPoint jp,Throwable exception){
        logger.info("[Throwable] Throwable_Method："+jp.getSignature().getName());

        if (exception instanceof Exception){
            logger.info("[Throwable] Exception："+exception.getMessage());
        }else {
            logger.info("[Throwable] Error："+exception.getMessage());
        }
    }
}
