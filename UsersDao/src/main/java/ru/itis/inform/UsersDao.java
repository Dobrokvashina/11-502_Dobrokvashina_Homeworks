package ru.itis.inform;


import java.util.List;

public interface UsersDao {

    List<User>  findAll();

    void save(User user);

    User find(int id);
}
