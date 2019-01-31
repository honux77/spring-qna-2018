package net.honux.qna.web;


import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable=false, length=64, unique = true)
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


     public boolean matchUid(Long uid) {
        return this.uid.equals(uid);
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
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

    @Override
    public boolean equals(Object other) {
        User user = (User) other;
        return this.uid.equals(user.uid);
    }
}
