package com.chemmstools.server.aop;

import com.chemmstools.server.annotations.AuthPermission;
import com.chemmstools.server.beans.AuthorizeParams;
import com.chemmstools.server.beans.User;
import com.chemmstools.server.service.TokenService;
import com.chemmstools.server.utils.ResultUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AuthPermissionAspect {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResultUtils resultUtils;

    @Pointcut("@annotation(com.chemmstools.server.annotations.AuthPermission)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint proceedingJoinPoint){
        try {
            User user= (User) getProceedingJoinPointArg(proceedingJoinPoint, User.class);
            if(user==null){
                return resultUtils.sendResult("400","参数错误","");
            }
            AuthPermission authPermission=((MethodSignature)proceedingJoinPoint.getSignature()).getMethod().getAnnotation(AuthPermission.class);
            for(String permission:authPermission.values()){
                if(permission.equals(user.getPermission())){
                    return proceedingJoinPoint.proceed();
                }
            }
            return resultUtils.sendResult("400","权限验证错误","");
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
