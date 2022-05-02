package com.example.timeoff.models;

public class User {

    private String mail;
    private String password;
    private String gender;
    public User(String mail, String password, String gender) {
        this.mail = mail;
        this.password = password;
        this.gender = gender;
    }
    public User() {}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", Gender='" + gender + '\'' +
                '}';
    }
}
