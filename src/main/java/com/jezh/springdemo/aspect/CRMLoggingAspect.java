package com.jezh.springdemo.aspect;

import com.jezh.springdemo.entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

    //  ----------------------------------------------  setup logger
    private final Logger log = LogManager.getLogger(getClass().getName()); // по умолчанию было бы дано такое же имя

    //    ----------------------------------------------  setup pointcut declarations
//    pattern: "any return-type-pattern    declaring-type pattern: .any class    .any method  (any arguments)"
    @Pointcut("execution(* com.jezh.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    //    @Pointcut("execution(* com.jezh.springdemo.service.*.*(..)) || execution(* com.jezh.springdemo.serviceImpl.*.*(..))")
    @Pointcut("execution(* com.jezh.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    //    @Pointcut("execution(* com.jezh.springdemo.dao.*.*(..)) || execution(* com.jezh.springdemo.daoImpl.*.*(..))")
    @Pointcut("execution(* com.jezh.springdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

//    NB: если бы я определял aspect или advice внутри другого класса, я бы сделал над классом соотв.аннотации @Aspect
// и @Component, сделал бы методы @Pointcut публичными (но не статическими!), и внутри аннотаций @Pointcut, @Before и т.п.
// писал бы "com.jezh.springdemo.aspect.CRMLoggingAspect.forControllerPackage()" и т.п.
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    //    ----------------------------------------------  add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display the method we are calling
        String method = joinPoint.getSignature().toString();
        log.info("@Before: calling method: " + method);
        // display the arguments to the method
        Object[] args = joinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        if (args.length == 0) builder.append("method without args");
        else {
            for (int i = 0; i < args.length; i++) {
                builder.append(String.format("arg[%d]: %s = %s; ", i, args[i].getClass().getCanonicalName(), String.valueOf(args[i])));
            }
        }
        log.debug(builder.toString());
    }

//    ----------------------------------------------  add @AfterReturning advice

    @AfterReturning(pointcut = "forAppFlow()",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.traceEntry("log.traceEntry");
        log.trace("@AfterReturning: " + joinPoint.getSignature().toString());
        log.trace("result: " + result);
        log.traceExit(result);
    }

//    ----------------------------------------------try to add @AfterReturning advice with argNames
//    join point where the the executing method has an @Transactional annotation:
//    @AfterReturning(value = "* ..*()&& @annotation(org.springframework.transaction.annotation.Transactional) && args(id)",
//    returning = "cust", argNames = "id, cust")
//    public void anyCustomerServiceImplMethod(int id, Customer cust) {
//        log.error("WOW-WOW");
//    }



//    ----------------------------------------------try to add @Around advice with args


}
