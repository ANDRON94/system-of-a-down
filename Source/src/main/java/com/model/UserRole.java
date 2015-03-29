package com.model;

import javax.persistence.*;

/**
 * Created by root on 14.03.15.
 */
@Entity
@Table(name = "role", schema = "", catalog = "calendar")
public class UserRole {
    private int userRoleId;
    private String role;
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (userRoleId != userRole.userRoleId) return false;
        if (role != null ? !role.equals(userRole.role) : userRole.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
