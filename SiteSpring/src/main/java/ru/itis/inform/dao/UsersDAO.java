package ru.itis.inform.dao;


import ru.itis.inform.model.User;
import java.util.List;

public interface UsersDAO extends BaseDao<User> {

    User find(String login);
}
