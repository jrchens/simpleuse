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


    public static String getInertErr(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT_ERR");
        buffer.append("::");
        buffer.append(DateTime.now().toString("yyyy-MM-dd HH:mm:ss.SSS"));
        buffer.append("::");
        buffer.append(UUID.randomUUID().toString());
        return buffer.toString();
    }

    public static String getRemoveErr(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("REMOVE_ERR");
        buffer.append("::");
        buffer.append(DateTime.now().toString("yyyy-MM-dd HH:mm:ss.SSS"));
        buffer.append("::");
        buffer.append(UUID.randomUUID().toString());
        return buffer.toString();
    }

    public static String getUpdateErr(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE_ERR");
        buffer.append("::");
        buffer.append(DateTime.now().toString("yyyy-MM-dd HH:mm:ss.SSS"));
        buffer.append("::");
        buffer.append(UUID.randomUUID().toString());
        return buffer.toString();
    }

    public static String getQueryErr(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("QUERY_ERR");
        buffer.append("::");
        buffer.append(DateTime.now().toString("yyyy-MM-dd HH:mm:ss.SSS"));
        buffer.append("::");
        buffer.append(UUID.randomUUID().toString());
        return buffer.toString();
    }
}
