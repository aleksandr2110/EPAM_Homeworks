package com.faculty.aspectXML;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aleksandr on 29.11.2020.
 */
public class GetExecutionTimeXML {

    private final Logger LOG = LoggerFactory.getLogger(GetExecutionTimeXML.class);

    // our file aspectBeans.xml has location in classpath root and connected by @ImportResource("classpath:/aspectBeans.xml")
    public Object executionTimeAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endOfExecutionTime = System.currentTimeMillis() - start;
        LOG.info("The " + proceedingJoinPoint.getSignature().getName() + " method from " + proceedingJoinPoint.getTarget().getClass()
                + " worked " + endOfExecutionTime + " milliseconds. XML schema");
        return result;
    }
}
