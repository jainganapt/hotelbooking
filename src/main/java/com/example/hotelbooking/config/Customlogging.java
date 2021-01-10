package com.example.hotelbooking.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Customlogging {

	@Around("execution(* com.example.hotelbooking.repo..*(..))")
	public Object repoProfile(ProceedingJoinPoint pjp) throws Throwable {
		return logTime(pjp);
	}

	@Around("execution(* com.example.hotelbooking.service..*(..))")
	public Object webLayerProfile(ProceedingJoinPoint pjp) throws Throwable {
		return logTime(pjp);
	}

	@Around("execution(* com.example.hotelbooking.controller..*(..))")
	public Object controlLayerProfile(ProceedingJoinPoint pjp) throws Throwable {
		return logTime(pjp);
	}

	private Object logTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("******* Method :: " + pjp.getSignature().getName() + " Execution time: " + elapsedTime
				+ " milliseconds. *******");

		return output;
	}

}