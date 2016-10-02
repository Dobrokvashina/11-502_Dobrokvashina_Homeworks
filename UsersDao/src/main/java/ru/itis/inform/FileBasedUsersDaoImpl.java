package ru.itis.inform;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileBasedUsersDaoImpl implements UsersDao {

    private File fileName;
    private List<User> list;

    public FileBasedUsersDaoImpl(File fileName) {
        this.fileName = fileName;

        if(!fileName.exists()){
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> findAll() {

        list = new LinkedList<User>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileName);

            while (scanner.hasNext()) {

                int id = scanner.nextInt();

                String name = scanner.next();

                String city = scanner.next();

                int age = scanner.nextInt();

                User human = new User(id, name, city, age);

                list.add(human);
            }

            scanner.close();

        } catch (Exception e) {

            new FileNotFoundException("Something wrong with file");
        }

        return list;
    }

    //Mockito, UsersService содержит  is Registered(name)

    public void save(User user) {

        if (list == null)
            list = new LinkedList<User>();

        list.add(user);

        try {
            if(!fileName.exists()){
                fileName.createNewFile();
            }


            PrintWriter out = new PrintWriter(fileName.getAbsoluteFile());

            Iterator<User> iterator = list.iterator();

            try {

                while (iterator.hasNext()) {
                    out.println(iterator.next().toString());
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User find(int id) {
        boolean find = false;
        User hereYouAre = null;

        if (!list.isEmpty()) {
            Iterator<User> iterator = list.iterator();

            while (iterator.hasNext() && !find) {
                hereYouAre = iterator.next();
                if (hereYouAre.getId() == id) {
                    find = true;
                }
            }
        }

        if (find) {
            return hereYouAre;
        }else {
            new IllegalArgumentException("There is no such man...");
            return null;
        }
    }
}
