package com.yjx.aspect;

import com.yjx.annoation.RequirePermission;
import com.yjx.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {

    @Autowired
    private HttpSession session;

    //切面会拦截指定包下面的指定注解
    //RequirePermission拦截注解
    @Around(value = "@annotation(com.yjx.annoation.RequirePermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result=null;
        //得到登录下面的拥有的权限
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        //判断是否有权限
        if(null==permissions || permissions.size()==0){
            //抛出异常认证
            throw  new AuthException();
        }
        //得到对应的目标  拿到你想要执行的那个方法
        MethodSignature methodSignature= (MethodSignature) pjp.getSignature();
        //得到方法上面的注解  拿到方法上面注解写的需要什么权限
        RequirePermission requirePermission= methodSignature.getMethod().getDeclaredAnnotation(RequirePermission.class);
        //判断注解上面对应的状态码   拿到那个权限之后 判断这个字符串是不是在list里面
        if(!(permissions.contains(requirePermission.code()))){
            //没有权限 就抛出异常   如果不在  直接不让执行
            throw  new AuthException();
        }
        //校验通过了  才会去执行你的方法
        result=pjp.proceed();
        return result;
    }

}
