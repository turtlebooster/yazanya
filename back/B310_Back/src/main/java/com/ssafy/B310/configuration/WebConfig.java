package com.ssafy.B310.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.B310.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {


	@Autowired
	JwtInterceptor jwtInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");		
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/v2/api-docs",
									 "/swagger-resources/**",
									 "/swagger-ui.html",
									 "/webjars/**");
				
	}
	
	@Value("${resource.path}")
	private String resourcePath;

	@Value("${upload.path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler(uploadPath)
				.addResourceLocations(resourcePath);
	}
}
