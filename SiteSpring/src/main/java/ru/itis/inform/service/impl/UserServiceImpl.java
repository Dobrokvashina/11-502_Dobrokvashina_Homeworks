package ru.itis.inform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.inform.dao.UsersDAO;
import ru.itis.inform.model.User;
import ru.itis.inform.service.UserService;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public User find(Long id) {
        return usersDAO.find(id);
    }

    @Override
    public void delete(Long id) {
        usersDAO.delete(id);
    }

    @Override
    public Long save(User user) {
        return usersDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return usersDAO.findAll();
    }

    @Override
    public void update(User user) {
        usersDAO.update(user);
    }

    @Override
    public boolean isRegistered(Long id) {
        if(usersDAO.find(id) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean isRegistered(String login) {
        if(usersDAO.find(login) == null)
            return false;
        else
            return true;
    }
}
