package guava;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashingTestCase {
    private static final Logger logger = LoggerFactory.getLogger(HashingTestCase.class);

    @Test
    public void hashing() throws Exception {

        String md5 = com.google.common.hash.Hashing.md5().hashString("123", com.google.common.base.Charsets.UTF_8).toString();

        logger.info("md5: {}", md5);
    }
}
