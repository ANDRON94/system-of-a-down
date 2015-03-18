package com.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 14.03.15.
 */
@Entity
@Table(name = "user")
public class User {
    private int iduser;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String key;
    private boolean enabled;
    private Set<UserRole> userRole = new HashSet<UserRole>(0);
    private String keyActivate;


    @Id
    @Column(name = "iduser",nullable = false,unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled",nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result =  0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "email",unique = true,nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "key_activate")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="rolelist",
            joinColumns={@JoinColumn(name="user_iduser", referencedColumnName="iduser")},
            inverseJoinColumns={@JoinColumn(name="role_user_role_id", referencedColumnName="user_role_id")})
    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

}
