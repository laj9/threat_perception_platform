package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName log
 */
public class Log implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String eventId;

    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Date类型的时间戳（用于存入数据库和平台端传递）
     */
    private Date timestamp;

    /**
     *
     */
    private String mac;

    /**
     *
     */
    private String eventName;

    /**
     *
     */
    private String eventDesc;

    /**
     * 上传至平台时间
     */

    public String getTimestampStr() {
        return timestampStr;
    }

    public void setTimestampStr(String timestampStr) {
        this.timestampStr = timestampStr;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 字符串类型的时间戳（用于接收客户端数据）
     */
    private String timestampStr;

    /**
     *
     */
    private String extraInfo;

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
    public String getEventId() {
        return eventId;
    }

    /**
     *
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
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
    public String getEventName() {
        return eventName;
    }

    /**
     *
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     *
     */
    public String getEventDesc() {
        return eventDesc;
    }

    /**
     *
     */
    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }


    /**
     *
     */
    public String getExtraInfo() {
        return extraInfo;
    }

    /**
     *
     */
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
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
        Log other = (Log) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getEventId() == null ? other.getEventId() == null : this.getEventId().equals(other.getEventId()))
                && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
                && (this.getEventName() == null ? other.getEventName() == null : this.getEventName().equals(other.getEventName()))
                && (this.getEventDesc() == null ? other.getEventDesc() == null : this.getEventDesc().equals(other.getEventDesc()))
                && (this.getExtraInfo() == null ? other.getExtraInfo() == null : this.getExtraInfo().equals(other.getExtraInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEventId() == null) ? 0 : getEventId().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getEventName() == null) ? 0 : getEventName().hashCode());
        result = prime * result + ((getEventDesc() == null) ? 0 : getEventDesc().hashCode());
        result = prime * result + ((getExtraInfo() == null) ? 0 : getExtraInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eventId=").append(eventId);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", mac=").append(mac);
        sb.append(", eventName=").append(eventName);
        sb.append(", eventDesc=").append(eventDesc);
        sb.append(", extraInfo=").append(extraInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}