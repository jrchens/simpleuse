package cn.com.simpleuse.sys.domain;

public class UserRoleRel {
    private Long id;

    private String username;

    private String rolename;

    private Integer srt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getSrt() {
        return srt;
    }

    public void setSrt(Integer srt) {
        this.srt = srt;
    }
}