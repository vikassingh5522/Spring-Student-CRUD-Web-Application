package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Logs method entry with arguments
    @Before("@annotation(com.example.annotation.Loggable)")
    public void logBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        logger.debug("🔍 Entering method: {} | Args: {}", method, Arrays.toString(args));
    }

    // Logs method exit (without printing entire result object to avoid large memory usage)
    @AfterReturning(pointcut = "@annotation(com.example.annotation.Loggable)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();

        // Avoid logging full object if it's too large (e.g., student list)
        String resultSummary = (result instanceof java.util.Collection)
                ? "Collection of size " + ((java.util.Collection<?>) result).size()
                : String.valueOf(result);

        logger.debug("✅ Exiting method: {} | Result: {}", method, resultSummary);
    }

    // Logs exceptions thrown from methods
    @AfterThrowing(pointcut = "@annotation(com.example.annotation.Loggable)", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();
        logger.error("❌ Exception in method: {} | Message: {}", method, ex.getMessage(), ex);
    }
}
