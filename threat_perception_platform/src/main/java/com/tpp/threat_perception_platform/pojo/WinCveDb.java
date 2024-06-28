package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;

/**
 * Windows漏洞与KB补丁关系库
 * @TableName win_cve_db
 */
public class WinCveDb implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 数据日期
     */
    private String dt;

    /**
     * CVE id
     */
    private String cve;

    /**
     * 各产品的CVSS Score平均值
     */
    private String score;

    /**
     * 漏洞影响范围，以productid集表示
     */
    private String productIdList;

    /**
     * 漏洞对应补丁号合集
     */
    private String kbList;

    /**
     * cvrf id
     */
    private String cvrfId;

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 数据日期
     */
    public String getDt() {
        return dt;
    }

    /**
     * 数据日期
     */
    public void setDt(String dt) {
        this.dt = dt;
    }

    /**
     * CVE id
     */
    public String getCve() {
        return cve;
    }

    /**
     * CVE id
     */
    public void setCve(String cve) {
        this.cve = cve;
    }

    /**
     * 各产品的CVSS Score平均值
     */
    public String getScore() {
        return score;
    }

    /**
     * 各产品的CVSS Score平均值
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 漏洞影响范围，以productid集表示
     */
    public String getProductIdList() {
        return productIdList;
    }

    /**
     * 漏洞影响范围，以productid集表示
     */
    public void setProductIdList(String productIdList) {
        this.productIdList = productIdList;
    }

    /**
     * 漏洞对应补丁号合集
     */
    public String getKbList() {
        return kbList;
    }

    /**
     * 漏洞对应补丁号合集
     */
    public void setKbList(String kbList) {
        this.kbList = kbList;
    }

    /**
     * cvrf id
     */
    public String getCvrfId() {
        return cvrfId;
    }

    /**
     * cvrf id
     */
    public void setCvrfId(String cvrfId) {
        this.cvrfId = cvrfId;
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
        WinCveDb other = (WinCveDb) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDt() == null ? other.getDt() == null : this.getDt().equals(other.getDt()))
            && (this.getCve() == null ? other.getCve() == null : this.getCve().equals(other.getCve()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getProductIdList() == null ? other.getProductIdList() == null : this.getProductIdList().equals(other.getProductIdList()))
            && (this.getKbList() == null ? other.getKbList() == null : this.getKbList().equals(other.getKbList()))
            && (this.getCvrfId() == null ? other.getCvrfId() == null : this.getCvrfId().equals(other.getCvrfId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDt() == null) ? 0 : getDt().hashCode());
        result = prime * result + ((getCve() == null) ? 0 : getCve().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getProductIdList() == null) ? 0 : getProductIdList().hashCode());
        result = prime * result + ((getKbList() == null) ? 0 : getKbList().hashCode());
        result = prime * result + ((getCvrfId() == null) ? 0 : getCvrfId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dt=").append(dt);
        sb.append(", cve=").append(cve);
        sb.append(", score=").append(score);
        sb.append(", productIdList=").append(productIdList);
        sb.append(", kbList=").append(kbList);
        sb.append(", cvrfId=").append(cvrfId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}