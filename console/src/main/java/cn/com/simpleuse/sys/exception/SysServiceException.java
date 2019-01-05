package cn.com.simpleuse.sys.exception;

public class SysServiceException extends RuntimeException {
    private Integer code;
    private String msg;

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

    public SysServiceException(Integer code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
