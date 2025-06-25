package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Intercepts methods annotated with @Loggable to log their execution.
     */
    @Around("@annotation(com.example.annotation.Loggable)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // Log method entry
        logger.trace("🔍 Entering method: {}.{}() | Args: {}", className, methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();

            // Log method successful exit
            logger.info("✅ Exiting method: {}.{}() | Result: {}", className, methodName, result);
            return result;
        } catch (Throwable ex) {
            // Log exception
            logger.error("❌ Exception in method: {}.{}() | Message: {}", className, methodName, ex.getMessage(), ex);
            throw ex; // Re-throw exception after logging
        }
    }
}
