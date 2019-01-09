package cn.com.simpleuse.sys.util;

import org.joda.time.DateTime;

import java.util.UUID;

public class ErrUtil {

    public static String getErrCode(String code){
        StringBuffer buffer = new StringBuffer();
        buffer.append(code);
        buffer.append("::");
        buffer.append(DateTime.now().toString("yyyy-MM-dd HH:mm:ss.SSS"));
        buffer.append("::");
        buffer.append(UUID.randomUUID().toString());
        return buffer.toString();
    }
}
