package cn.com.simpleuse.sys.exception;

public class SysServiceException extends RuntimeException {

    public SysServiceException() {
        super();
    }

    public SysServiceException(String message) {
        super(message);
    }

    public SysServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysServiceException(Throwable cause) {
        super(cause);
    }
    protected SysServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
