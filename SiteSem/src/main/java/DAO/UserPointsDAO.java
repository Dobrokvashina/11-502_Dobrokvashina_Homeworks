package DAO;


public interface UserPointsDAO {

    int[] find(int id);

    int[][] findAll();

    void save(int[] extra);

    void delete(int id);

    void update(int[] extra);
}
