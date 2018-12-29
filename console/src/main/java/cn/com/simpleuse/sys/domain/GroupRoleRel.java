package cn.com.simpleuse.sys.domain;

public class GroupRoleRel {
    private Long id;

    private String groupname;

    private String rolename;

    private Integer srt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
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