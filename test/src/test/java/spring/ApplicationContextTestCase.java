package spring;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class ApplicationContextTestCase {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextTestCase.class);
    private static ApplicationContext applicationContext;

//    @Before is executed before each test
//    @Before

    @BeforeClass
    public static void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-test.xml");
    }

    @Test
    public void conn() throws Exception {
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        logger.info("dataSource == null : {}", dataSource == null);
        Connection connection = dataSource.getConnection();
        logger.info("connection == null : {}", connection == null);
    }


}
