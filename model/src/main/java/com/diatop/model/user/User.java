package com.diatop.model.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.NonNull;

@Data
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @NonNull
    private String email;

    private String password;

    private String birthNumber;


    private String role;

    public User() {
    }

    public User(String password, String birthNumber, String email, String role) {
        this.password = password;
        this.birthNumber = birthNumber;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", birthNumber='" + birthNumber + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
