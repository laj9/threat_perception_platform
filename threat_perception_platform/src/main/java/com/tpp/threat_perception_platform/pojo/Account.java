package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;

/**
 * 
 * @TableName account
 */
public class Account implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String mac;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String fullName;

    /**
     * 
     */
    private String sid;

    /**
     * 
     */
    private String sidType;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String disabled;

    /**
     * 
     */
    private String lockout;

    /**
     * 密码是否修改
     */
    private String passwordChangeable;

    /**
     * 
     */
    private String passwordExpires;

    /**
     * 是否允许空密码
     */
    private String passwordRequired;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getMac() {
        return mac;
    }

    /**
     * 
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     */
    public String getSid() {
        return sid;
    }

    /**
     * 
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 
     */
    public String getSidType() {
        return sidType;
    }

    /**
     * 
     */
    public void setSidType(String sidType) {
        this.sidType = sidType;
    }

    /**
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * 
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    /**
     * 
     */
    public String getLockout() {
        return lockout;
    }

    /**
     * 
     */
    public void setLockout(String lockout) {
        this.lockout = lockout;
    }

    /**
     * 密码是否修改
     */
    public String getPasswordChangeable() {
        return passwordChangeable;
    }

    /**
     * 密码是否修改
     */
    public void setPasswordChangeable(String passwordChangeable) {
        this.passwordChangeable = passwordChangeable;
    }

    /**
     * 
     */
    public String getPasswordExpires() {
        return passwordExpires;
    }

    /**
     * 
     */
    public void setPasswordExpires(String passwordExpires) {
        this.passwordExpires = passwordExpires;
    }

    /**
     * 是否允许空密码
     */
    public String getPasswordRequired() {
        return passwordRequired;
    }

    /**
     * 是否允许空密码
     */
    public void setPasswordRequired(String passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Account other = (Account) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFullName() == null ? other.getFullName() == null : this.getFullName().equals(other.getFullName()))
            && (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getSidType() == null ? other.getSidType() == null : this.getSidType().equals(other.getSidType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getLockout() == null ? other.getLockout() == null : this.getLockout().equals(other.getLockout()))
            && (this.getPasswordChangeable() == null ? other.getPasswordChangeable() == null : this.getPasswordChangeable().equals(other.getPasswordChangeable()))
            && (this.getPasswordExpires() == null ? other.getPasswordExpires() == null : this.getPasswordExpires().equals(other.getPasswordExpires()))
            && (this.getPasswordRequired() == null ? other.getPasswordRequired() == null : this.getPasswordRequired().equals(other.getPasswordRequired()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getSidType() == null) ? 0 : getSidType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getLockout() == null) ? 0 : getLockout().hashCode());
        result = prime * result + ((getPasswordChangeable() == null) ? 0 : getPasswordChangeable().hashCode());
        result = prime * result + ((getPasswordExpires() == null) ? 0 : getPasswordExpires().hashCode());
        result = prime * result + ((getPasswordRequired() == null) ? 0 : getPasswordRequired().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mac=").append(mac);
        sb.append(", name=").append(name);
        sb.append(", fullName=").append(fullName);
        sb.append(", sid=").append(sid);
        sb.append(", sidType=").append(sidType);
        sb.append(", status=").append(status);
        sb.append(", disabled=").append(disabled);
        sb.append(", lockout=").append(lockout);
        sb.append(", passwordChangeable=").append(passwordChangeable);
        sb.append(", passwordExpires=").append(passwordExpires);
        sb.append(", passwordRequired=").append(passwordRequired);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}