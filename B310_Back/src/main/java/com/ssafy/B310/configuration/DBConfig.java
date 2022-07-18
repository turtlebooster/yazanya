package com.ssafy.B310.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.B310.mapper")
public class DBConfig {}
