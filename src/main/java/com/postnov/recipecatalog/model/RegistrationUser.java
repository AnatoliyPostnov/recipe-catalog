package com.postnov.recipecatalog.model;

import com.postnov.recipecatalog.enums.UserRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "registration_user")
public class RegistrationUser {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
}
