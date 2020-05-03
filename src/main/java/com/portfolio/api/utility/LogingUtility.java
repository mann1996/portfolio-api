package com.portfolio.api.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogingUtility {
	private Logger logger = LogManager.getLogger(this.getClass());

	@AfterThrowing(pointcut = "execution(* com.portfolio.api.service.*Impl.*(..))", throwing = "ex")
	public void logService(Exception ex) throws Exception {
		logger.error(ex);
	}
}
