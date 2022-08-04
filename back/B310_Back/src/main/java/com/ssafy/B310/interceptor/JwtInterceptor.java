package com.ssafy.B310.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.service.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
	JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean check = checkAnnotation(handler, NoJwt.class);
        if(check) return true;
        
        return jwtService.isUsable(request.getHeader("access-token"));
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
