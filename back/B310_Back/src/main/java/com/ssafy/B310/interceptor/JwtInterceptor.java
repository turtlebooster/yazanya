package com.ssafy.B310.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.service.JwtService;
import com.ssafy.B310.service.UserService;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserService userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check = checkAnnotation(handler, NoJwt.class);
        if(check) return true;
       
        String refreshToken = request.getHeader("refresh-token");
        String accessToken = request.getHeader("access-token"); 
        
        if (accessToken != null && jwtService.isValidAccessToken(accessToken)) {
            return true;
        }

        response.setStatus(401);
        response.setHeader("ACCESS_TOKEN", accessToken);
        response.setHeader("REFRESH_TOKEN", refreshToken);
        response.setHeader("msg", "Check the tokens.");
        return false;
	}
	
    private boolean checkAnnotation(Object handler,Class cls){
        HandlerMethod handlerMethod=(HandlerMethod) handler;
      //해당 어노테이션이 존재하면 true.
        if(handlerMethod.getMethodAnnotation(cls) != null){ 
            return true;
        }
        return false;
    }
	
}
