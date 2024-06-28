package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName weak_pwd
 */
public class WeakPwd implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * mac地址
     */
    private String mac;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 弱口令
     */
    private String weakPwd;

    /**
     * 扫描时间
     */
    private Date time;

    /**
     * 任务发起者
     */
    private String taskSender;

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
     * mac地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * mac地址
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 账户名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 账户名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 弱口令
     */
    public String getWeakPwd() {
        return weakPwd;
    }

    /**
     * 弱口令
     */
    public void setWeakPwd(String weakPwd) {
        this.weakPwd = weakPwd;
    }

    /**
     * 扫描时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 扫描时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 任务发起者
     */
    public String getTaskSender() {
        return taskSender;
    }

    /**
     * 任务发起者
     */
    public void setTaskSender(String taskSender) {
        this.taskSender = taskSender;
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
        WeakPwd other = (WeakPwd) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
                && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
                && (this.getWeakPwd() == null ? other.getWeakPwd() == null : this.getWeakPwd().equals(other.getWeakPwd()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
                && (this.getTaskSender() == null ? other.getTaskSender() == null : this.getTaskSender().equals(other.getTaskSender()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getWeakPwd() == null) ? 0 : getWeakPwd().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getTaskSender() == null) ? 0 : getTaskSender().hashCode());
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
        sb.append(", accountName=").append(accountName);
        sb.append(", weakPwd=").append(weakPwd);
        sb.append(", time=").append(time);
        sb.append(", taskSender=").append(taskSender);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}