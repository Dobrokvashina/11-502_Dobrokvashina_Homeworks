package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPSQL {

    private static Connection connection;
    private static ConnectionPSQL connectionPSQL;

    static {
        if (connectionPSQL == null)
            try {
                connectionPSQL = new ConnectionPSQL();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }



    private Properties properties = new Properties();


    private ConnectionPSQL() throws FileNotFoundException {
        try {

            Class.forName("org.postgresql.Driver");

            if (connection == null) {

                properties.load(

                        new FileInputStream("C:\\Users\\Саоша\\IdeaProjects\\SiteSem\\resources\\db.properties"));

                        connection = DriverManager.getConnection(properties.getProperty("db.host"),

                        properties.getProperty("db.login"), properties.getProperty("db.password"));

            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }



    public static Connection getConnection() {

        return connection;

    }
}
