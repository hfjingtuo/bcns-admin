package com.mainiway.interceptor;

import com.mainiway.annotation.AuthAccess;
import com.mainiway.bean.po.TokenModel;
import com.mainiway.bean.po.UserInfo;
import com.mainiway.exception.NoTokenException;
import com.mainiway.exception.TokenErrorException;
import com.mainiway.service.IUserInfoService;
import com.mainiway.service.RedisTokenService;
import com.mainiway.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:14
 * Copyright: Copyright (c) 2018
 * Description： 自定义拦截器，判断此次请求是否有权限
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private RedisTokenService manager;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        AuthAccess methodAnnotation = method.getAnnotation(AuthAccess.class);
        // 有 @AuthAccess 注解，需要认证
        if (methodAnnotation != null) {
            //从http 请求头header中得到token
            String token = request.getHeader(Constants.AUTHORIZATION);
            if (token == null) {
                throw new NoTokenException("Token Null");
            }
            //验证token
            TokenModel model = manager.getToken(token);
            String userName = model.getUserName();
            if (manager.checkToken(model)) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constants.CURRENT_USER_NAME, userName);
                UserInfo user = userInfoService.getUserInfoByUserName(userName);
                if (user == null) {
                    throw new TokenErrorException("Token Invalid");
                }
                request.setAttribute(Constants.CURRENT_USER, user);
                return true;
            }
            //如果验证token失败，并且方法注明了Authorization，返回401错误
            if (method.getAnnotation(AuthAccess.class) != null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
