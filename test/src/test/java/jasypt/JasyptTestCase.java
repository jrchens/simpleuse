package jasypt;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JasyptTestCase {

    private static final Logger logger = LoggerFactory.getLogger(JasyptTestCase.class);


    @Test
    public void encrty() throws Exception {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("BN2sBTWFTtyFgOBrWBfHcA=="); // YBoqLUXP , 1
        String myEncryptedText = textEncryptor.encrypt("simpleuse");
        logger.info("{}", myEncryptedText);

        String plainText = textEncryptor.decrypt(myEncryptedText);
        logger.info("{}", plainText);


    }
}


