package com.pkrm.aspect;

import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectBefore {
	final static Logger logger = Logger.getLogger(AspectBefore.class);
	
	@Before("execution(* com.pkrm.emp.usingJdbcTemplate.EmployeeDao.*(..))")
	public void beforeMethodDao(JoinPoint joinPoint) {
		logger.info("before Dao method "+joinPoint.getSignature().getName());
	}
	
	@Before("execution(* com.pkrm.emp.usingJdbcTemplate.EmployeeService.*(..))")
	public void beforeMethodService(JoinPoint joinPoint) {
		logger.info("before Service method "+joinPoint.getSignature().getName());
	}
}
