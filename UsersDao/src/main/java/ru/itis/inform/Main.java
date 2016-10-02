package ru.itis.inform;

import java.io.File;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        File file = new File("input/users.txt");

        UsersDao fileDao = new FileBasedUsersDaoImpl(file);

        User tony = new User(10, "Tony" , "Malibu", 40);

        fileDao.save(new User(2 , "Edwin", "England", 70));
        fileDao.save(tony);
        fileDao.save(new User(3 , "Pepper", "Manhattan", 37));
        fileDao.save(new User(4 , "Rhodey", "New-York", 45));

        System.out.println(tony.getId());
        System.out.println(fileDao.find(tony.getId()).toString());

        UsersService service = new UsersService(fileDao);

        System.out.println(service.isRegistered("Edwin"));
        System.out.println(service.isRegistered("Rhodey"));
        System.out.println(service.isRegistered("Obi"));
        System.out.println(service.isRegistered("Tony"));



        UsersDao jbbc = new JdbcImpl("jdbc:postgresql://localhost:5432/UsersCars", "postgres", "female");

        jbbc.save(tony);

        List<User> list = jbbc.findAll();


        for (User user : list) {
            System.out.println(user.toString());
        }

        UsersService serv = new UsersService(jbbc);


        System.out.println(serv.isRegistered("Sasha"));

        System.out.println(serv.isRegistered("Obi"));

    }
}
