package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName hotfix
 */
public class Hotfix implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 主机MAC地址
     */
    private String mac;

    /**
     * 补丁序号
     */
    private String hotfixId;

    /**
     * 漏洞列表
     */
    private List<WinCveDb> winCveDbs;

    public List<WinCveDb> getWinCveDbs() {
        return winCveDbs;
    }

    public void setWinCveDbs(List<WinCveDb> winCveDbs) {
        this.winCveDbs = winCveDbs;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主机MAC地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * 主机MAC地址
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 补丁序号
     */
    public String getHotfixId() {
        return hotfixId;
    }

    /**
     * 补丁序号
     */
    public void setHotfixId(String hotfixId) {
        this.hotfixId = hotfixId;
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
        Hotfix other = (Hotfix) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
                && (this.getHotfixId() == null ? other.getHotfixId() == null : this.getHotfixId().equals(other.getHotfixId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getHotfixId() == null) ? 0 : getHotfixId().hashCode());
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
        sb.append(", hotfixId=").append(hotfixId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}