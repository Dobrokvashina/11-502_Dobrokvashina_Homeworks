package DAO;

import Classes.Speciality;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class SpecialityDAOImpl implements SpecialityDAO {

    private Connection connection;
    private Statement st;
    private List<Speciality> list;


    SpecialityDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Speciality find(int id) {
        return null;
    }


    public List<Speciality> findAll() {
        return null;
    }


    public void save(Speciality speciality) {

    }


    public void delete(int id) {

    }


    public void update(Speciality speciality) {

    }
}
