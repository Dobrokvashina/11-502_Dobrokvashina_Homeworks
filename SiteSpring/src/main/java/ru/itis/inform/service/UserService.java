package ru.itis.inform.service;

import ru.itis.inform.model.User;

public interface UserService extends BaseService<User> {

    boolean isRegistered(Long id);

    boolean isRegistered(String login);
}
