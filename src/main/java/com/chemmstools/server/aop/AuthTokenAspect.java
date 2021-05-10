package com.chemmstools.server.aop;

import com.chemmstools.server.beans.AuthorizeParams;
import com.chemmstools.server.service.TokenService;
import com.chemmstools.server.utils.ResultUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@Aspect
public class AuthTokenAspect {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResultUtils resultUtils;

    @Pointcut("@annotation(com.chemmstools.server.annotations.AuthToken)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint proceedingJoinPoint){
        try {
            switch (proceedingJoinPoint.getSignature().getName()){
            }
            AuthorizeParams authorizeParams= (AuthorizeParams) getProceedingJoinPointArg(proceedingJoinPoint,AuthorizeParams.class);
            if(authorizeParams==null){
                return resultUtils.sendResult("400","参数错误","");
            }
            if(!tokenService.authToken(authorizeParams.getToken())){
                return resultUtils.sendResult("400","token验证失败","");
            }
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "error";
    }

    private Object getProceedingJoinPointArg(ProceedingJoinPoint proceedingJoinPoint,Class clazz){
        if(proceedingJoinPoint==null||proceedingJoinPoint.getArgs()==null||proceedingJoinPoint.getArgs().length==0){
            return null;
        }
        for(Object oj:proceedingJoinPoint.getArgs()){
            if(clazz.isInstance(oj)){
                return oj;
            }
        }
        return null;
    }
}
