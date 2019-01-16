package reflect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.UUID;

public class MethodTestCase {

    private static final Logger logger = LoggerFactory.getLogger(MethodTestCase.class);

    @Test
    public void invokeGetMethodTest() throws Exception {

        SomeBean bean = new SomeBean();
        bean.setName("class SomeBean instant bean, setName SomeBean");
        Method method = bean.getClass().getMethod("getName",null);
        Object result = method.invoke(bean);
        logger.info("{}",result);
    }

    @Test
    public void invokeSetMethodTest() throws Exception {
        String uuid = UUID.randomUUID().toString();
        SomeBean bean = new SomeBean();
        Method method = bean.getClass().getMethod("setName",String.class);
        method.invoke(bean,uuid);
        logger.info("uuid    {}",uuid);
        logger.info("getName {}",bean.getName());
    }
}
