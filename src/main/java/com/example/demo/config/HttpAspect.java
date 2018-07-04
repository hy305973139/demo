package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.util.UuidRandom;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 记录用户行为
 * @author huang
 *
 */
@Aspect
@Configuration
public class HttpAspect {

    @Autowired
    private UserDao userDao;
 
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);
    /**
     * 监控所有controller
     */
    @Pointcut(value = "execution(public * com.example.demo.controller.*.*(..))")
    public void p(){}
    /**
     * 返回用户id、请求路径、请求方法、调用的远程方法、ip
     * @param joinPoint
     */
    @Before("p()") 
    public void doBefore(JoinPoint joinPoint){
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userid = request.getParameter("userid");
        if(null != userid){
            User user = userDao.findUserById(userid);
            if(null==user){
                user =new User();
                user.setId(userid);
                user.setKahao(UuidRandom.randomHexString()+"000000000000000000000000000000000000000000000000");
                user.setPowernumber(0);
                userDao.addUser(user);
            }
        }
    }

    @After("p()")
    public void doAfter(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
    }
}