package Utils;


import DAO.ConnectionPSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Verifier {

    private Connection connection;
    private Statement st;

    public Verifier() {
        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void existAchivement(int userId) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM achivements WHERE (id =" + userId + ")"
            );

            if (!resultSet.next()) {
                throw new IllegalAccessException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
