package net.honux.qna.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long uid;

    @Column(nullable=false, length=64)
    private String email;
    
    @Column(length=32)
    private String name;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Long getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void update(User newUser) {
        this.name = newUser.name;
        this.email = newUser.email;
        if (newUser.password.length() > 0) {
            this.password = newUser.password;
        }
    }
}
