package com.yjx.interceptor;

import com.yjx.utils.CookieUtil;
import com.yjx.utils.LoginUserUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                              Object handler) throws Exception {
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if (userId == 0) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }
        // 将用户信息放入request，供Freemarker模板使用
        String userName = CookieUtil.getCookieValue(request, "userName");
        String trueName = CookieUtil.getCookieValue(request, "trueName");
        String roleName = CookieUtil.getCookieValue(request, "roleName");
        if (userName != null) {
            request.setAttribute("uname", userName);
        }
        if (trueName != null) {
            request.setAttribute("tname", trueName);
        }
        if (roleName != null) {
            request.setAttribute("rname", roleName);
        }
        return true;
    }
}
