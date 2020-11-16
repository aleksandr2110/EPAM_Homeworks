package com.faculty.bpp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Aleksandr on 16.11.2020.
 */
@Component
public class TimedWithBPP implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TimedWithBPP.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("Inside post process before initialization: " + beanName);

        Class type = bean.getClass();
        if (type.isAnnotationPresent(Timed.class)) { // check for some annotation
            logger.info("Annotated class: " + type );
            Object proxy = Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), new InvocationHandler()
            {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                {
                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, args);
                    long after = System.nanoTime();
                    logger.info("Method executed in " + (after - before) + " nanoseconds");
                    return retVal;
                }
            });
            return proxy;
        }
        else
        {
            return bean;
        }
    }
    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        logger.info("Inside post process after initialization: " + beanName);
        return bean;
    }
}
