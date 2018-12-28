package spring;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ELTestCase {

    private static final Logger logger = LoggerFactory.getLogger(ELTestCase.class);
    private static ApplicationContext applicationContext;

//    @Before is executed before each test
//    @Before

    @BeforeClass
    public static void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-test.xml");
    }

    @Test
    public void desc() throws Exception {
        ComboPooledDataSource dataSource = applicationContext.getBean("dataSource", ComboPooledDataSource.class);
        logger.info("dataSource.description: {}", dataSource.getDescription());
    }
}
