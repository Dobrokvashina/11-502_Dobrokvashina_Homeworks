package ru.itis.inform;

public class User {
    private String city;
    private String name;
    private int age;
    private  int id;

    public User(int id, String name, String city, int age) {
        this.city = city;
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String login) {
        this.city = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + city + " " + age;
    }
}
