package com.pkrm.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectAround {

	final static Logger logger = Logger.getLogger(AspectAround.class);

	@Around("execution(* com.pkrm.emp.usingJdbcTemplate.EmployeeDao.*(..))")
	public Object afterMethodDao(ProceedingJoinPoint proceedingJoinPoint) {
		long start= System.currentTimeMillis();
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long stop = System.currentTimeMillis();
		logger.info("time taken for Dao method "+proceedingJoinPoint.getSignature().getName()+" is "+(stop-start)+" milliseconds");
		return value;

	}

	@Around("execution(* com.pkrm.emp.usingJdbcTemplate.EmployeeService.*(..))")
	public Object afterMethodService(ProceedingJoinPoint proceedingJoinPoint) {
		long start= System.currentTimeMillis();
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long stop = System.currentTimeMillis();
		logger.info("time taken for Service method "+proceedingJoinPoint.getSignature().getName()+" is "+(stop-start)+" milliseconds");
		return value;

	}
}
