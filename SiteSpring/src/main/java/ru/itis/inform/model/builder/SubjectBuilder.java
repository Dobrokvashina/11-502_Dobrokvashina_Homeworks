package ru.itis.inform.model.builder;

import ru.itis.inform.model.Subject;
import ru.itis.inform.model.User;

public class SubjectBuilder {
    private Long id;
    private String name;
    private int point;
    private User user;

    public SubjectBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public SubjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SubjectBuilder setPoint(int point) {
        this.point = point;
        return this;
    }

    public SubjectBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Subject createSubject() {
        return new Subject(id, name, point, user);
    }
}