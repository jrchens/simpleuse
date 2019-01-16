package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class ClassATest {
    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
            ClassA a = applicationContext.getBean(ClassA.class);
            a.read(1d,null);
        } catch (Exception e){

        }
    }
}
