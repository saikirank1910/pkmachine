package com.pkrm.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectBefore {
	final static Logger logger = Logger.getLogger(AspectBefore.class);
	
	@Before("execution(* com.pkrm.dao.PersonDao.*(..))")
	public void beforeMethodPersonDao(JoinPoint joinPoint) {
		logger.info("before Dao method "+joinPoint.getSignature().getName());
		 System.out.println(joinPoint.getArgs()[0]);
	}
	
	@Before("execution(* com.pkrm.dao.UserRoleMapDao.*(..))")
	public void beforeMethodUserRoleMapDao(JoinPoint joinPoint) {
		logger.info("before Dao method "+joinPoint.getSignature().getName());
		 System.out.println(joinPoint.getArgs()[0]);
	}
	
}
