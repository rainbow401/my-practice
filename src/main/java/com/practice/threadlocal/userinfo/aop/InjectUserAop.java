package com.practice.threadlocal.userinfo.aop;

import com.practice.threadlocal.userinfo.annotation.InjectUser;
import com.practice.threadlocal.userinfo.context.ServiceContext;
import com.practice.threadlocal.userinfo.enums.InjectType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author yanzhihao
 * @date 2023/1/11
 * @Description
 */
@Aspect
@Component
public class InjectUserAop {

    @Resource
    private ServiceContext ctx;

    @Pointcut("@annotation(com.practice.threadlocal.userinfo.annotation.InjectUser)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature )joinPoint.getSignature();
        Method method = signature.getMethod();
        InjectUser annotation = method.getAnnotation(InjectUser.class);
        Object result = null;
        if (InjectType.AOP.equals(annotation.type())) {
            ctx.extract();
            result = joinPoint.proceed(args);
            ctx.clear();
        } else {
            result = joinPoint.proceed(args);
        }
        return result;
    }
}
