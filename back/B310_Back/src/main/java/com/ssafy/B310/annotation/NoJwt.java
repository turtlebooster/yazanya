package com.ssafy.B310.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// annotation 레벨 설정
@Retention(RetentionPolicy.RUNTIME)
//선언된 어노테이션이 적용될수 있는 위치를 결정. TYPE-class,interface,enum에 적용.
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface NoJwt {
}
