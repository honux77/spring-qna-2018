package net.honux.qna.web;

public class User {
    private static int guid = 0;
    private int uid;
    private String email;
    private String name;
    private String password;
    private String password2;

    public User() {
        guid++;
        this.uid = guid; 
    }

    public User(String email, String name, String password, String password2) {
        this();
        this.email = email;
        this.name = name;
        this.password = password;
        this.password2 = password2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    public String getPassword2() {
        return password2;
    }

    public int getUid() {
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
}
