package aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class ClassAAdvice implements MethodBeforeAdvice , AfterReturningAdvice,ThrowsAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ClassAAdvice.class);
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        try {
            logger.info("ClassAAdvice ======= MethodBefore :{}",method.getName());
        } catch (Exception e){
            logger.error("ClassAAdvice ======= MethodBefore.err",e);
        }
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        try {
            logger.info("ClassAAdvice ======= AfterReturning :{}",method.getName());
        } catch (Exception e){
            logger.error("ClassAAdvice ======= AfterReturning.err",e);
        }
    }

    public void afterThrowing( Exception ex) {
        logger.error("ClassAAdvice ======= Exception: {}",ex.getMessage());
    }

//    public void afterThrowing(Method method, Object[] args, Object target, NullPointerException ex) {
//        logger.error("ClassAAdvice ======= NullPointerException  {}:{}",target.getClass().getName(),method.getName());
//    }
}
