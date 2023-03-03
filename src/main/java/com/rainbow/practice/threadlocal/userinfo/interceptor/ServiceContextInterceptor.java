package com.rainbow.practice.threadlocal.userinfo.interceptor;

import com.rainbow.practice.threadlocal.userinfo.annotation.InjectUser;
import com.rainbow.practice.threadlocal.userinfo.context.ServiceContext;
import com.rainbow.practice.threadlocal.userinfo.enums.InjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
//@Component
public class ServiceContextInterceptor implements HandlerInterceptor {

    @Autowired
    private ServiceContext ctx;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        InjectUser annotation = method.getAnnotation(InjectUser.class);
        if (annotation != null && InjectType.INTERCEPTOR.equals(annotation.type())) {
            ctx.extract();
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        InjectUser annotation = method.getAnnotation(InjectUser.class);
        if (annotation != null && InjectType.INTERCEPTOR.equals(annotation.type())) {
            ctx.clear();
        }
    }
}
