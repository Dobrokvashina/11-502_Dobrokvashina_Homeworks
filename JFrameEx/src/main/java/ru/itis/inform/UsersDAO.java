package ru.itis.inform;


import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UsersDAO {

    private Connection connection;
    private Statement st;
    private List<User> list;
    private static int id;

    public UsersDAO() {

        id =1;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UsersCars", "postgres", "female");

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
            ResultSet res = st.executeQuery(
                    "SELECT * FROM users"
            );

            while (res.next()) {

                list.add(new User(res.getInt("us_id"), res.getString("name"), res.getString("surname"), res.getInt("univgroup"), res.getBoolean("iskazan")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String[][] findAllToStr() {
        String[][] strs = new String[findAll().size()][5];
        Iterator<User> it = findAll().iterator();
        int i =0;
        while (it.hasNext()) {
            strs[i] = it.next().toStringAr();
            i++;
        }
        return strs;
    }

    public void save(User user) {

        try {
            st.executeUpdate(
                    "INSERT INTO Users(us_id, name, surname, univgroup, iskazan) VALUES ("+ id+", '" + user.getName() +"', '" + user.getSurname() + "', " + user.getGroup() + ", " + user.isKazan() + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            st.executeUpdate(
                    "DELETE FROM users WHERE (us_id =" + id +")"
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
            return null;
        }
    }
}
