package cn.com.simpleuse.sys.domain;

import cn.com.simpleuse.sys.bean.BaseDomain;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.hibernate.validator.constraints.SafeHtml;

import java.util.Date;

public class Group /*extends BaseDomain*/ {
    private Long id;

    private String groupname;

    private String viewname;

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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname == null ? null : viewname.trim();
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