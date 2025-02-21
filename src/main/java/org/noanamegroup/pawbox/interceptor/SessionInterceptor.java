package org.noanamegroup.pawbox.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 排除登录和注册接口
        String path = request.getRequestURI();
        if (path.contains("/user/login") || path.contains("/user/register")) {
            return true;
        }
        
        // 检查session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            return true;
        }
        
        // session无效,返回未授权状态码
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
} 