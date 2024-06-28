package com.tpp.threat_perception_platform.pojo;

import java.io.Serializable;

/**
 *
 * @TableName role
 */
public class Role implements Serializable {
    /**
     *
     */
    private Integer roleId;

    /**
     *
     */
    private String roleName;

    /**
     *
     */
    private String roleDesc;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     *
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     *
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     *
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     *
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     *
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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
        Role other = (Role) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
                && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
                && (this.getRoleDesc() == null ? other.getRoleDesc() == null : this.getRoleDesc().equals(other.getRoleDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleDesc() == null) ? 0 : getRoleDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleDesc=").append(roleDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}