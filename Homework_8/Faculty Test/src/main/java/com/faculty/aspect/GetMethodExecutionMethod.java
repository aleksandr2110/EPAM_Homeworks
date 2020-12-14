package com.faculty.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Aleksandr on 29.11.2020.
 */
@Aspect
@Component
public class GetMethodExecutionMethod {

    private final Logger LOG = LoggerFactory.getLogger(GetMethodExecutionMethod.class);

    @Pointcut("@annotation(com.faculty.aspect.ExecutionTime)")
    public void annotatedMethod(){ }

    @Around("annotatedMethod()")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endOfExecutionTime = System.currentTimeMillis() - start;
        LOG.info("The " + proceedingJoinPoint.getSignature().getName() + " method from " + proceedingJoinPoint.getTarget().getClass()
                + " worked " + endOfExecutionTime + " milliseconds");
        return result;
    }
}
