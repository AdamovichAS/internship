package com.andersenlab.adamovichjr.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilingAspect {

    @Pointcut("execution(public * com.andersenlab.adamovichjr.*.*(..))")
    public void callQuoterImplPublic(){}

    @Before("callQuoterImplPublic()")
    public void beforeCallQuoterImplPublic(JoinPoint jp){
        System.out.println("aspect before");
    }

    @After("callQuoterImplPublic()")
    public void afterCallQuoterImplPublic(JoinPoint jp){
        System.out.println("aspect after");
    }
}
