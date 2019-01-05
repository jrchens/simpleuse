package cn.com.simpleuse.sys.exception;

public class SysControllerException extends RuntimeException {
    private Integer code;
    private String msg;

    public SysControllerException() {
        super();
    }

    public SysControllerException(String message) {
        super(message);
    }

    public SysControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysControllerException(Throwable cause) {
        super(cause);
    }
    protected SysControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SysControllerException(Integer code, String msg) {
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
