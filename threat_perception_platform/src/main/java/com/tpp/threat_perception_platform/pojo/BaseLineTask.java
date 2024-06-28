package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName base_line_task
 */
public class BaseLineTask implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务时间
     */
    private Date taskTime;

    /**
     * 任务状态（0：未执行，1：已执行，2：暂停）
     */
    private Integer taskStatus;

    /**
     *
     */
    private String taskHosts;

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
     * 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 任务时间
     */
    public Date getTaskTime() {
        return taskTime;
    }

    /**
     * 任务时间
     */
    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    /**
     * 任务状态（0：未执行，1：已执行，2：暂停）
     */
    public Integer getTaskStatus() {
        return taskStatus;
    }

    /**
     * 任务状态（0：未执行，1：已执行，2：暂停）
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     *
     */
    public String getTaskHosts() {
        return taskHosts;
    }

    /**
     *
     */
    public void setTaskHosts(String taskHosts) {
        this.taskHosts = taskHosts;
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
        BaseLineTask other = (BaseLineTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
                && (this.getTaskTime() == null ? other.getTaskTime() == null : this.getTaskTime().equals(other.getTaskTime()))
                && (this.getTaskStatus() == null ? other.getTaskStatus() == null : this.getTaskStatus().equals(other.getTaskStatus()))
                && (this.getTaskHosts() == null ? other.getTaskHosts() == null : this.getTaskHosts().equals(other.getTaskHosts()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskTime() == null) ? 0 : getTaskTime().hashCode());
        result = prime * result + ((getTaskStatus() == null) ? 0 : getTaskStatus().hashCode());
        result = prime * result + ((getTaskHosts() == null) ? 0 : getTaskHosts().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskTime=").append(taskTime);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", taskHosts=").append(taskHosts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}