package com.example.huahuoimg.filter;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.example.huahuoimg.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xiang
 * @Date: 2021/5/7 20:56
 * <p>
 * 拦截器：验证用户是否登录
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //http的header中获得token
//        String token = request.getHeader(JWTUtils.USER_LOGIN_TOKEN);
//        //token不存在
//        if (token == null || token.equals(""))  return false;
//        //验证token
//        String sub = JWTUtils.validateToken(token);
//        if (sub == null || sub.equals(""))
//            return false;
//        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
//        if (JWTUtils.isNeedUpdate(token)){
//            String newToken = JWTUtils.createToken(sub);
//            response.setHeader(JWTUtils.USER_LOGIN_TOKEN,newToken);
//        }
        return true;
    }
}