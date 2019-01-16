package aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassA {
    private static final Logger logger = LoggerFactory.getLogger(ClassA.class);

    public void read(Double i,Double j){
        try {
            logger.info("Method ClassA.read()");
            logger.info("{}/{}={}",i,j,i/j);
        } catch (Exception e){
            logger.error("ClassA.read.err",e);
            throw new RuntimeException("ClassA.read.err");
        }
    }
}
