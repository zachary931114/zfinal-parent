package me.zhoubl.zfinal.common.service.aop;

import me.zhoubl.zfinal.common.ex.BizEx;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by zhoubl on 2017/2/15.
 */
@Aspect
@Component
public class LogExceptionAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogExceptionAspect.class);

	@Pointcut("execution(* me.zhoubl.zfinal.*.service.*.*(..)) || execution(* me.zhoubl.zfinal.*.service.*.*(..)) ")
	private void anyMethod() {
	}

	@AfterThrowing(throwing = "ex", pointcut = "anyMethod()")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		if (ex instanceof BizEx) {
			// BizEx bizEx = (BizEx) ex;
			// logger.info("==>LogExceptionAspect.BizEx");
			// logger.info("==>info class: " +
			// joinPoint.getSignature().getDeclaringTypeName());
			// logger.info("==>info method: " +
			// joinPoint.getSignature().getName());
			// logger.info("==>errCode:" + bizEx.getErrorCode() + " errMsg:" +
			// bizEx.getMsg());
			// logger.info("==>" + bizEx.fillInStackTrace());
		} else {
			logger.error("==>LogExceptionAspect.Exception");
			logger.error("==>Error class: " + joinPoint.getSignature().getDeclaringTypeName());
			logger.error("==>Error method: " + joinPoint.getSignature().getName());

			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				logger.error("==>args[" + i + "]: " + joinPoint.getArgs());
			}

			logger.error("==>Exception class: " + ex.getClass().getName());
			logger.error("==>" + ex.fillInStackTrace());
			ex.printStackTrace();
		}
	}
}
