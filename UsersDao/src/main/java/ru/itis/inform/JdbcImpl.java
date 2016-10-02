package ru.itis.inform;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import org.postgresql.*;


public class JdbcImpl implements UsersDao{

    private Connection connection;
    private Statement st;
    private List<User> list;

    public JdbcImpl(String url, String name, String password) {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            st = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> findAll(){

        list = new LinkedList<User>();

        try {
            ResultSet  res = st.executeQuery(
                "SELECT * FROM Users"
            );

            while (res.next()) {

                list.add(new User(res.getInt("user_id"), res.getString("user_name"), res.getString("user_city"), res.getInt("user_age")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void save(User user) {

        try {
            st.executeUpdate(
                    "INSERT INTO Users(user_name, user_city, user_age) VALUES ( '" + user.getName() +"', '" + user.getCity() + "', " + user.getAge() + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User find(int id) {
        boolean find = false;
        User hereYouAre = null;

        if (!list.isEmpty()) {
            Iterator<User> iterator = list.iterator();

            while (iterator.hasNext() && !find) {
                hereYouAre = iterator.next();
                if (hereYouAre.getId() == id)
                    find = true;

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
