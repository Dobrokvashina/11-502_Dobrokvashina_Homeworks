package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ExtraPointsDAOImpl implements ExtraPointsDAO {

    private Connection connection;
    private Statement st;


    ExtraPointsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int[] find(int univ_id, int ach_id) {
        int[] points = new int[3];
        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM ExtraPoints WHERE (univ_id =" + univ_id + " AND  ach_id =" + ach_id +")"
            );

            while (res.next()) {
                points[0] = univ_id;
                points[1] = ach_id;
                points[2] = res.getInt("points");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }


    public int[][] findAll() {

        int[][] points = new int[0][0];

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM ExtraPoints"
            );
            points = new int[res.getRow()][5];
            int i = 0;
            while (res.next()) {
                points[i][0] = res.getInt("univ_id");
                points[i][1] = res.getInt("ach_id");
                points[i][2] = res.getInt("points");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }


    public void save(int univ_id, int ach_id, int points) {

        try {
            st.executeUpdate(
                    "INSERT INTO ExtraPoints VALUES ( " + univ_id +", " + ach_id +", " + points +" )"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int univ_id, int ach_id) {

        try {
            st.executeUpdate(
                    "DELETE FROM ExtraPoints WHERE (univ_id =" + univ_id + ", ach_id =" + ach_id + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int univ_id, int ach_id, int points) {

        try {
            st.executeUpdate(
                    "UPDATE costs SET points =" + points + " WHERE (univ_id =" + univ_id + " AND  ach_id =" + ach_id +")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
