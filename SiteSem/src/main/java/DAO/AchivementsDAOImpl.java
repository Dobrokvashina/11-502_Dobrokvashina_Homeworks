package DAO;

import Classes.Achivement;
import Classes.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


class AchivementsDAOImpl implements AchivementsDAO {

    private Connection connection;
    private Statement st;
    private List<Achivement> list;

    AchivementsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Achivement find(int id) {

        Achivement ach = null;
        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM achivements WHERE (id =" + id + ")"
            );

            SubjectsDAO subjectsDAO = DAOFactory.getSubjectsDAO();
            Subject subject = subjectsDAO.find(res.getInt("ach_sub"));
            ach = new Achivement(subject, res.getString("ach_name"), res.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ach;
    }


    public List<Achivement> findAll() {

        list = new LinkedList<Achivement>();

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM achivements"
            );

            SubjectsDAO subjectsDAO = DAOFactory.getSubjectsDAO();
            Subject subject;
            Achivement ach;
            while (res.next()) {

                subject = subjectsDAO.find(res.getInt("ach_sub"));
                ach = new Achivement(subject, res.getString("ach_name"), res.getInt("id"));
                list.add(ach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    public void save(Achivement achivement) {

        try {
            st.executeUpdate(
                    "INSERT INTO achivements(ach_sub, ach_name) VALUES ( " + achivement.getSubject().getId() +", '" + achivement.getType() + "')"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int id) {

        try {
            st.executeUpdate(
                    "DELETE FROM achivements WHERE (id =" + id + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(Achivement achivement) {

        try {
            st.executeUpdate(
                    "UPDATE achivements SET ach_sub =" + achivement.getSubject().getId() + ", ach_name = '" + achivement.getType() + "' WHERE id =" + achivement.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
