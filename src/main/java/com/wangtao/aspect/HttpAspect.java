package com.wangtao.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

private static final Logger logger=LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 切入点，用来指明advice所作用的方法，基于表达式的拦截条件
     */
    @Pointcut("execution(public * com.wangtao.controller.GirlController.girlAdd(..))")
public void log(){

}

@Before("log()")
    public void doBefore(JoinPoint joinpoint){
    ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request=attributes.getRequest();

    //url
    logger.info("url={}",request.getRequestURI());

    //method GET还是POST等等
    logger.info("method={}",request.getMethod());
    //ip
    logger.info("ip={}",request.getRemoteAddr());
    //port
    logger.info("port={}",request.getServerPort());
    //类方法
    logger.info("class_method={}",joinpoint.getSignature().getDeclaringType()+"."+joinpoint.getSignature().getName());

    //参数
    //logger.info("params={}",joinpoint.getArgs());



    }

    /**
     * 获取response中返回的参数.to_string()
     * @param object
     */
    @AfterReturning(pointcut = "log()",returning = "object")
    public void doAfterReturning(Object object){
        logger.info("params{}",object.toString());
    }




@After("log()")
    public void doAfter(){
logger.info("22222222");
}




}
