package ru.itis.inform;

import java.util.Iterator;
import java.util.List;


public class UsersService {

    private UsersDao dao;

    public UsersService(UsersDao input) {
        dao = input;
    }


    public boolean isRegistered(String name) {

        boolean registration = false;

        List<User> users = dao.findAll();

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(name)) {
                registration = true;
                break;
            }
        }

        return registration;
    }

}
