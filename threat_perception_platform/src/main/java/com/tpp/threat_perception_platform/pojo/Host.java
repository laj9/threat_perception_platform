package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName host
 */
public class Host implements Serializable {
    /**
     * 自增ID
     */
    private Integer hostId;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 主机ip
     */
    private String ip;

    /**
     * 主机mac地址
     */
    private String mac;

    /**
     * 操作系统名称
     */
    private String osName;

    /**
     * 操作系统版本
     */
    private String osVersion;

    /**
     * 操作系统架构
     */
    private String osArch;

    /**
     * 操作系统类型
     */
    private String osType;

    /**
     * cpu信息
     */
    private String cpu;

    /**
     * 内存大小信息
     */
    private String ram;

    /**
     * 0：下线，1：在线
     */
    private Integer status;

    /**
     * 系统第一次上线时间
     */
    private Date createTime;

    /**
     * 数据最近一次更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    public Integer getHostId() {
        return hostId;
    }

    /**
     * 自增ID
     */
    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    /**
     * 主机名
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * 主机名
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * 主机ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 主机ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 主机mac地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * 主机mac地址
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 操作系统名称
     */
    public String getOsName() {
        return osName;
    }

    /**
     * 操作系统名称
     */
    public void setOsName(String osName) {
        this.osName = osName;
    }

    /**
     * 操作系统版本
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * 操作系统版本
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    /**
     * 操作系统架构
     */
    public String getOsArch() {
        return osArch;
    }

    /**
     * 操作系统架构
     */
    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    /**
     * 操作系统类型
     */
    public String getOsType() {
        return osType;
    }

    /**
     * 操作系统类型
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }

    /**
     * cpu信息
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * cpu信息
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * 内存大小信息
     */
    public String getRam() {
        return ram;
    }

    /**
     * 内存大小信息
     */
    public void setRam(String ram) {
        this.ram = ram;
    }

    /**
     * 0：下线，1：在线
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0：下线，1：在线
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 系统第一次上线时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 系统第一次上线时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 数据最近一次更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 数据最近一次更新时间
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
        Host other = (Host) that;
        return (this.getHostId() == null ? other.getHostId() == null : this.getHostId().equals(other.getHostId()))
                && (this.getHostname() == null ? other.getHostname() == null : this.getHostname().equals(other.getHostname()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
                && (this.getOsName() == null ? other.getOsName() == null : this.getOsName().equals(other.getOsName()))
                && (this.getOsVersion() == null ? other.getOsVersion() == null : this.getOsVersion().equals(other.getOsVersion()))
                && (this.getOsArch() == null ? other.getOsArch() == null : this.getOsArch().equals(other.getOsArch()))
                && (this.getOsType() == null ? other.getOsType() == null : this.getOsType().equals(other.getOsType()))
                && (this.getCpu() == null ? other.getCpu() == null : this.getCpu().equals(other.getCpu()))
                && (this.getRam() == null ? other.getRam() == null : this.getRam().equals(other.getRam()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHostId() == null) ? 0 : getHostId().hashCode());
        result = prime * result + ((getHostname() == null) ? 0 : getHostname().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getOsName() == null) ? 0 : getOsName().hashCode());
        result = prime * result + ((getOsVersion() == null) ? 0 : getOsVersion().hashCode());
        result = prime * result + ((getOsArch() == null) ? 0 : getOsArch().hashCode());
        result = prime * result + ((getOsType() == null) ? 0 : getOsType().hashCode());
        result = prime * result + ((getCpu() == null) ? 0 : getCpu().hashCode());
        result = prime * result + ((getRam() == null) ? 0 : getRam().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", hostId=").append(hostId);
        sb.append(", hostname=").append(hostname);
        sb.append(", ip=").append(ip);
        sb.append(", mac=").append(mac);
        sb.append(", osName=").append(osName);
        sb.append(", osVersion=").append(osVersion);
        sb.append(", osArch=").append(osArch);
        sb.append(", osType=").append(osType);
        sb.append(", cpu=").append(cpu);
        sb.append(", ram=").append(ram);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}