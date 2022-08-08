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
        
        String result = request.getHeader("refresh-token"); //jwt 토큰
        String requestUserId = jwtService.getUserID(result);
        
        //현재 로그인 되어있는 유저
        User user = userService.myPage(requestUserId);
        
        //DB에 저장되어있는 refresh token과 같고 && 해당 refresh token이 유효하고
        if(user.getRefreshToken().equals(result) && jwtService.isUsable(request.getHeader("refresh-token")) ) {
        	// access token도 유효하다면 ---> 통과
        	if(jwtService.isUsable(request.getHeader("access-token"))) {
        		return true;        		
        	} 
        	// access token은 시간 지나서 재발급 받아야 하면 ---> access token 재발급 해주고 통과
        	else {
        		jwtService.createAccessToken("userId", user.getUserId(), "access-token");
        		
        		return true;
        	}
        }
        //DB에 저장된 refresh token과 다르거나 || refresh token 유효기간이 지나서 재발급 받아야 한다면 ---> 재로그인
        else {
        	System.out.println("여기야?");
        	return false;
        }
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
