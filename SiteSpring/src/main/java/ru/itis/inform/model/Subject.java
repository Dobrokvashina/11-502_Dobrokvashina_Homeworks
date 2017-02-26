package ru.itis.inform.model;

import javax.jws.soap.SOAPBinding;

public class Subject {
    private Long id;
    private String name;
    private int point;
    private User user;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Subject(Long id, String name, int point, User user) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.user = user;
    }

    public Subject() {}

    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
        this.point = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
