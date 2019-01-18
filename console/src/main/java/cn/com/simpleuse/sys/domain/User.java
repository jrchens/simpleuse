package cn.com.simpleuse.sys.domain;

import cn.com.simpleuse.validator.constraints.Unique;
import cn.com.simpleuse.validator.group.Save;
import cn.com.simpleuse.validator.group.Update;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class User {
    private Long id;

    @Length(min = 3, max = 20, groups = {Save.class})
    @Unique(table = "sys_user", column = "username", groups = {Save.class, Update.class})
    private String username;

    @Length(min = 3, max = 100, groups = {Save.class, Update.class})
    private String viewname;

    @Length(min = 6, max = 200, groups = {Save.class})
    private String password;

    private Boolean deleted;

    private String cruser;

    private Date crtime;

    private String mduser;

    private Date mdtime;

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

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname == null ? null : viewname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser == null ? null : cruser.trim();
    }

    public Date getCrtime() {
        return crtime;
    }

    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    public String getMduser() {
        return mduser;
    }

    public void setMduser(String mduser) {
        this.mduser = mduser == null ? null : mduser.trim();
    }

    public Date getMdtime() {
        return mdtime;
    }

    public void setMdtime(Date mdtime) {
        this.mdtime = mdtime;
    }
}