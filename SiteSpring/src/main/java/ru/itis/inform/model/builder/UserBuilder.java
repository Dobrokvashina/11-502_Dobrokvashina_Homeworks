package ru.itis.inform.model.builder;

import ru.itis.inform.model.User;

public class UserBuilder {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String login;
    private String password;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public User createUser() {
        return new User(id, name, surname, country, city, login, password);
    }
}