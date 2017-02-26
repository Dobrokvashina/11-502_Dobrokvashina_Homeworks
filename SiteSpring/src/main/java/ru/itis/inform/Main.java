package ru.itis.inform;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.inform.config.AppConfig;
import ru.itis.inform.dao.UsersDAO;

/**
 * Created by Саоша on 17.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UsersDAO usersDAO= context.getBean(UsersDAO.class);
        usersDAO.findAll().forEach(System.out::println);
    }
}
