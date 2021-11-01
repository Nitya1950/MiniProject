package com.MCC.pediatric.centre.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class AdminEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean doctor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }
}
