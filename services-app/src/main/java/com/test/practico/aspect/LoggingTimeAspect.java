package com.test.practico.aspect;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingTimeAspect {
	
	
	@Pointcut("execution(* com.test.practico.service.*.* (..))")
	public void aplicaAntes() {};

	//@Before("aplicaAntes()")
	public void aplicarAntesJoinPoint(JoinPoint joinPoint) {
		log.info("==== executing before " +  joinPoint.getTarget().getClass()+" "+joinPoint.getSignature().getName());
		
	}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
  public void requestMappingAnnotations() {}
	
	
	@Around("requestMappingAnnotations()")
	public Object aroundAnnotationRequestMapping(ProceedingJoinPoint joinPoint)throws Throwable {
		log.info("==== executing requestMappingAnnotations before " +  joinPoint.getTarget().getClass()+" "+joinPoint.getSignature().getName());
		long timeIni = System.currentTimeMillis();
		Object result = null;
		try {
			result=joinPoint.proceed();
		}catch(Throwable thr  ) {
			throw thr;
		} finally{
			long time =  System.currentTimeMillis()-timeIni;
			log.info("==== executing requestMappingAnnotations after execution Time: " + time +" ms" );
		}
		
		return result;
		
	}
	
	
}
