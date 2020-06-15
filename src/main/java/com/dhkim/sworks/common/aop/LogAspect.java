package com.dhkim.sworks.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Aspect
@Component
public class LogAspect {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.dhkim.sworks..service..*(..))")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++){
			logger.info("args(" + i + ") : " + args[i]);
		}
		logger.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
		Object result = pjp.proceed();
		logger.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
		return result;
	}
}
