package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName base_line_result
 */
public class BaseLineResult implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 主机MAC
     */
    private String mac;

    /**
     * 策略名称
     */
    private String policyName;

    /**
     * 是否合格
     */
    private String qualification;

    /**
     * 描述
     */
    private String description;


    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主机MAC
     */
    public String getMac() {
        return mac;
    }

    /**
     * 主机MAC
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 策略名称
     */
    public String getPolicyName() {
        return policyName;
    }

    /**
     * 策略名称
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    /**
     * 是否合格
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * 是否合格
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
        BaseLineResult other = (BaseLineResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
                && (this.getPolicyName() == null ? other.getPolicyName() == null : this.getPolicyName().equals(other.getPolicyName()))
                && (this.getQualification() == null ? other.getQualification() == null : this.getQualification().equals(other.getQualification()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getPolicyName() == null) ? 0 : getPolicyName().hashCode());
        result = prime * result + ((getQualification() == null) ? 0 : getQualification().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
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
        sb.append(", policyName=").append(policyName);
        sb.append(", qualification=").append(qualification);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}