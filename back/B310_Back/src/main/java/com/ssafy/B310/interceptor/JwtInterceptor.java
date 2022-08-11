package com.ssafy.B310.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.dto.TokenResponse;
import com.ssafy.B310.jwt.JwtTokenProvider;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
	JwtTokenProvider jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod().equals("OPTIONS")) {
    		return true;
    	}

		boolean check = checkAnnotation(handler, NoJwt.class);
        if(check) return true;
       
        String accessToken = request.getHeader("access-token"); 
        
        if (accessToken != null && jwtService.isValidAccessToken(accessToken)) {
        	System.out.println("access token 일치 ---> 통과");
            return true;
        }

        System.out.println("access token 불일치 ---> 통과 XXXXXX");
        response.setStatus(401);
    	return false;

	}
	
    private boolean checkAnnotation(Object handler, Class cls){
        HandlerMethod handlerMethod = (HandlerMethod) handler;
      //해당 어노테이션이 존재하면 true.
        if(handlerMethod.getMethodAnnotation(cls) != null){ 
            return true;
        }
        return false;
    }
	
}
