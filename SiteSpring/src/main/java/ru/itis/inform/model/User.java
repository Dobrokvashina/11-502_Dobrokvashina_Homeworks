package ru.itis.inform.model;

import ru.itis.inform.model.builder.UserBuilder;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String city;
    private List<Subject> subjects;
    private List<Achivement> achivements;
    private String login;
    private String password;

    public User() {
    }

    public User(Long id, String name, String surname, String country, String city, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", subjects=" + subjects +
                ", achivements=" + achivements +
                ", login='" + login + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Achivement> getAchivements() {
        return achivements;
    }

    public void setAchivements(List<Achivement> achivements) {
        this.achivements = achivements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
