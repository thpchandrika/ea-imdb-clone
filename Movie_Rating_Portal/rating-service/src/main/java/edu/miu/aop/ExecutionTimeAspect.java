package edu.miu.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class ExecutionTimeAspect {

    @Pointcut("@annotation(edu.miu.aop.ExecutionTime)")
    public void executionTimeAnnotation() {
    }

    @Around("executionTimeAnnotation()")
    public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        var result = proceedingJoinPoint.proceed();
        long finish = System.currentTimeMillis();
        log.info("*****  " + proceedingJoinPoint.getSignature().getName() + " taken time in millis: " + (finish - start) + "  *****");
        return result;
    }

}
