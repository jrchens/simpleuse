package cn.com.simpleuse.sys.bean;

public class BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 8349550146744885427L;
    private String csrfToken;

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }
}
