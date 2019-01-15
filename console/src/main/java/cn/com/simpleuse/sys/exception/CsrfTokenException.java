package cn.com.simpleuse.sys.exception;

public class CsrfTokenException extends RuntimeException {
    public CsrfTokenException() {
        super();
    }

    public CsrfTokenException(String message) {
        super(message);
    }

    public CsrfTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsrfTokenException(Throwable cause) {
        super(cause);
    }

    protected CsrfTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
