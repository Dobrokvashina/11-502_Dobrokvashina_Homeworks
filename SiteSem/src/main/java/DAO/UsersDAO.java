package DAO;

import Classes.User;
import java.util.List;

public interface UsersDAO {

    User find(int id);

    List<User> findAll();

    void save(User user);

    void delete(int id);

    void update(User user);

}
