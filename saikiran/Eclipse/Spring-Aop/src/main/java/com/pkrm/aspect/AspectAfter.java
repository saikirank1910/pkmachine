package com.pkrm.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectAfter {
	 final static Logger logger = Logger.getLogger(AspectBefore.class);

	@After("execution(* com.pkrm.emp.usingJdbcTemplate.EmployeeDao.*(..))")
	public void afterMethodDao(JoinPoint joinPoint) {
		logger.info("after Dao method"+joinPoint.getSignature().getName());
	}
	@After("execution(* com.pkrm.emp.usingJdbcTemplate.EmployeeService.*(..))")
	public void afterMethodService(JoinPoint joinPoint) {
		logger.info("after Service method"+joinPoint.getSignature().getName());
	}
}
