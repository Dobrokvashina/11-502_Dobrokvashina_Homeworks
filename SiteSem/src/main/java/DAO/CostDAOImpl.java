package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CostDAOImpl implements CostDAO {

    private Connection connection;
    private Statement st;


    CostDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int[] find(int univ_id, int spec_id) {
        int[] cost = new int[5];

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM costs WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
            );

            while (res.next()) {
                cost[0] = univ_id;
                cost[1] = spec_id;
                cost[2] = res.getInt("day_contract");
                cost[3] = res.getInt("evening_form");
                cost[4] = res.getInt("correspondence_form");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cost;
    }


    public int[][] findAll() {

        int[][] costs = new int[0][0];

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM costs"
            );
            costs = new int[res.getRow()][5];
            int i = 0;
            while (res.next()) {
                costs[i][0] = res.getInt("univ_id");
                costs[i][1] = res.getInt("spec_id");
                costs[i][2] = res.getInt("day_contract");
                costs[i][3] = res.getInt("evening_form");
                costs[i][4] = res.getInt("correspondence_form");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return costs;
    }


    public void save(int univ_id, int spec_id, int[] cost) {

        try {
            st.executeUpdate(
                    "INSERT INTO costs VALUES ( " + univ_id +", " + spec_id +", " + cost[0] +", " + cost[1] +", " + cost[2] + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int univ_id, int spec_id) {

        try {
            st.executeUpdate(
                    "DELETE FROM costs WHERE (univ_id =" + univ_id + ", spec_id =" + spec_id + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int univ_id, int spec_id, int[] cost) {

        try {
            st.executeUpdate(
                    "UPDATE costs SET day_contract =" + cost[0] + ", evening_form = " + cost[1] + ", correspondence_form = " + cost[2] + " WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
