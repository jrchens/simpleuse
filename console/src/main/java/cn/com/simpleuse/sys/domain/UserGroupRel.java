package cn.com.simpleuse.sys.domain;

public class UserGroupRel {
    private Long id;

    private String username;

    private String groupname;

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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Integer getSrt() {
        return srt;
    }

    public void setSrt(Integer srt) {
        this.srt = srt;
    }
}