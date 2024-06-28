package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName permission
 */
public class Permission implements Serializable {
    /**
     * 
     */
    private Integer permId;

    /**
     * 
     */
    private String permName;

    /**
     * 
     */
    private String permDesc;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getPermId() {
        return permId;
    }

    /**
     * 
     */
    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    /**
     * 
     */
    public String getPermName() {
        return permName;
    }

    /**
     * 
     */
    public void setPermName(String permName) {
        this.permName = permName;
    }

    /**
     * 
     */
    public String getPermDesc() {
        return permDesc;
    }

    /**
     * 
     */
    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        Permission other = (Permission) that;
        return (this.getPermId() == null ? other.getPermId() == null : this.getPermId().equals(other.getPermId()))
            && (this.getPermName() == null ? other.getPermName() == null : this.getPermName().equals(other.getPermName()))
            && (this.getPermDesc() == null ? other.getPermDesc() == null : this.getPermDesc().equals(other.getPermDesc()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPermId() == null) ? 0 : getPermId().hashCode());
        result = prime * result + ((getPermName() == null) ? 0 : getPermName().hashCode());
        result = prime * result + ((getPermDesc() == null) ? 0 : getPermDesc().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permId=").append(permId);
        sb.append(", permName=").append(permName);
        sb.append(", permDesc=").append(permDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}