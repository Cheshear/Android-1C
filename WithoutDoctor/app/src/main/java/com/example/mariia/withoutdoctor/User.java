package com.example.mariia.withoutdoctor;

/**
 * Created by mariia on 29.11.2017.
 */

public class User {
    private String email;
    private String name;
    private String surname;
    private String passport;

    public User(String email, String name, String surname, String passport) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    public User() {
    }


    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassport() {
        return passport;
    }
}
