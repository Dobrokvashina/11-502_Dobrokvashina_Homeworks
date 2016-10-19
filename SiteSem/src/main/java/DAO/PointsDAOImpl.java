package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class PointsDAOImpl implements PointsDAO {

    private Connection connection;
    private Statement st;


    PointsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public int[] find(int univ_id, int spec_id) {
        int[] points = new int[6];

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM points WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
            );

            while (res.next()) {
                points[0] = univ_id;
                points[1] = spec_id;
                points[2] = res.getInt("budjet");
                points[3] = res.getInt("day_contract");
                points[4] = res.getInt("evening_form");
                points[5] = res.getInt("correspondence_form");
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
                    "SELECT * FROM points"
            );
            points = new int[res.getRow()][6];
            int i = 0;
            while (res.next()) {
                points[i][0] = res.getInt("univ_id");
                points[i][1] = res.getInt("spec_id");
                points[i][2] = res.getInt("budjet");
                points[i][3] = res.getInt("day_contract");
                points[i][4] = res.getInt("evening_form");
                points[i][5] = res.getInt("correspondence_form");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }


    public void save(int univ_id, int spec_id, int[] points) {

        try {
            st.executeUpdate(
                    "INSERT INTO points VALUES ( " + univ_id +", " + spec_id +", " + points[0] +", " + points[1] +", " + points[2] +", " + points[3] + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int univ_id, int spec_id) {

        try {
            st.executeUpdate(
                    "DELETE FROM points WHERE (univ_id =" + univ_id + ", spec_id =" + spec_id + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int univ_id, int spec_id, int[] points) {

        try {
            st.executeUpdate(
                    "UPDATE points SET budjet= " + points[0] +", day_contract =" + points[1] + ", evening_form = " + points[2] + ", correspondence_form = " + points[3] + " WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
