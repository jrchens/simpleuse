import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCase {

    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    @Test
    public void test() throws Exception {
        logger.info("start : {}", DateTime.now().toString());

//        Thread.sleep(3000);

        logger.info("  end : {}", DateTime.now().toString());

    }
}
