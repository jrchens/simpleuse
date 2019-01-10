package cn.com.simpleuse.sys.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class Config {
    private Long id;

    @NotBlank
    @Length(min = 4,max = 100)
    private String cfgCode;
    @NotBlank
    @Length(min = 1,max = 100)
    private String cfgName;
    @NotBlank
    @Length(min = 1,max = 200)
    private String cfgValue;

    private Boolean disabled;

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

    public String getCfgCode() {
        return cfgCode;
    }

    public void setCfgCode(String cfgCode) {
        this.cfgCode = cfgCode == null ? null : cfgCode.trim();
    }

    public String getCfgName() {
        return cfgName;
    }

    public void setCfgName(String cfgName) {
        this.cfgName = cfgName == null ? null : cfgName.trim();
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue == null ? null : cfgValue.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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