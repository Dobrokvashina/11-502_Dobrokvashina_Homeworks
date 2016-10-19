package DAO;


public interface UserAchiveDAO {

    int[] find(int id);

    int[][] findAll();

    void save(int[] extra);

    void delete(int id);

    void update(int[] extra);

}
