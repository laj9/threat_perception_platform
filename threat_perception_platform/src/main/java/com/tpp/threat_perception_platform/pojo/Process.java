package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;

/**
 *
 * @TableName process
 */
public class Process implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * MAC地址
     */
    private String mac;

    /**
     * PID
     */
    private String pid;

    /**
     * 父母PID
     */
    private String ppid;

    /**
     * 进程名称
     */
    private String name;

    /**
     * 命令行
     */
    private String cmd;

    /**
     * 进程优先级
     */
    private String priority;

    /**
     * 进程描述
     */
    private String description;

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
     * MAC地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * MAC地址
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * PID
     */
    public String getPid() {
        return pid;
    }

    /**
     * PID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 父母PID
     */
    public String getPpid() {
        return ppid;
    }

    /**
     * 父母PID
     */
    public void setPpid(String ppid) {
        this.ppid = ppid;
    }

    /**
     * 进程名称
     */
    public String getName() {
        return name;
    }

    /**
     * 进程名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 命令行
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * 命令行
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * 进程优先级
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 进程优先级
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * 进程描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 进程描述
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
        Process other = (Process) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
                && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
                && (this.getPpid() == null ? other.getPpid() == null : this.getPpid().equals(other.getPpid()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCmd() == null ? other.getCmd() == null : this.getCmd().equals(other.getCmd()))
                && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getPpid() == null) ? 0 : getPpid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCmd() == null) ? 0 : getCmd().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
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
        sb.append(", pid=").append(pid);
        sb.append(", ppid=").append(ppid);
        sb.append(", name=").append(name);
        sb.append(", cmd=").append(cmd);
        sb.append(", priority=").append(priority);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}