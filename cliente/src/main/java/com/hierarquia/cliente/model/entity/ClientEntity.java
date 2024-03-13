package com.hierarquia.cliente.model.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.security.Identity;

@Entity
@Table(name = "clientes")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="user_client")
    private String userClient;
    @Column(name="password_value")
    private String passwordValue;
    @Column(name="password_status")
    private String passwordStatus;
    @Column(name="password_client")
    private String passwordClient;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserClient() {
        return userClient;
    }

    public void setUserClient(String userClient) {
        this.userClient = userClient;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(String passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public String getPasswordClient() {
        return passwordClient;
    }

    public void setPasswordClient(String passwordClient) {
        this.passwordClient = passwordClient;
    }
}
