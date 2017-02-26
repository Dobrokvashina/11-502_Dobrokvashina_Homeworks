package ru.itis.inform.dao.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.inform.dao.UsersDAO;
import ru.itis.inform.model.builder.*;
import ru.itis.inform.model.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDAOImpl implements UsersDAO {

    // language=SQL
    private final static String FIND_USER = "SELECT * FROM users WHERE users.id = ?";
    // language=SQL
    private final static String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE users.user_login = ?";
    // language=SQL
    private final static String FIND_POINTS_FOR_USER = "SELECT * FROM user_points JOIN subjects ON user_points.subject_id = subjects.id WHERE user_id=?";
    // language=SQL
    private final static String FIND_POINTS_FOR_USER_BY_LOGIN = "SELECT * FROM (SELECT * FROM user_points JOIN (SELECT id FROM users  WHERE user_login=?) AS us ON user_points.user_id = us.id) AS u JOIN subjects ON u.subject_id = subjects.id";
    // language=SQL
    private final static String FIND_ALL_USERS = "SELECT * FROM users";
    //language=SQL
    private final static String FIND_ALL_POINTS = "SELECT * FROM user_points JOIN subjects ON user_points.subject_id = subjects.id";
    // language=SQL
    private final static String DELETE_USER = "DELETE  FROM users WHERE users.id = ?";
    // language=SQL
    private final static String DELETE_POINTS_FOR_USER = "DELETE  FROM user_points WHERE user_id = ?";
    // language=SQL
    private final static String UPDATE_USER = "UPDATE  users SET user_name=?,user_surname=?,country=?,city=?,user_login=?,user_pasword=? WHERE id = ?";
    // language=SQL
    private final static String SAVE_USER = "INSERT INTO users (user_name, user_surname, country, city, user_login, user_pasword) VALUES (?,?,?,?,?,?)";
    // language=SQL
    private final static String SAVE_POINTS_FOR_USER = "INSERT INTO user_points (user_id, subject_id, points) VALUES (?,?,?)";

    private JdbcTemplate jdbcTemplate;
    private Map<Long, User> usersMap;

    private RowMapper<User> rowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {

            User res = new UserBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("user_name")).setSurname(resultSet.getString("user_surname"))
                    .setCountry(resultSet.getString("country")).setCity(resultSet.getString("City")).setLogin(resultSet.getString("user_login"))
                    .setPassword(resultSet.getString("user_pasword")).createUser();

            usersMap.put(res.getId(),res);

            return res;
        }
    };

    private RowMapper<Subject> subjectRowMapper = new RowMapper<Subject>() {
        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            Subject sub = new SubjectBuilder().setId(resultSet.getLong("subject_id")).setName(resultSet.getString("sub_name"))
                    .setUser(usersMap.get(resultSet.getLong("user_id"))).setPoint(resultSet.getInt("points")).createSubject();

            usersMap.get(resultSet.getLong("user_id")).getSubjects().add(sub);

            return sub;
        }
    };

    @Autowired
    public UsersDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        usersMap = new HashMap<Long, User>();
    }


    @Override
    public User find(Long id) {

        usersMap = new HashMap<Long, User>();
        jdbcTemplate.query(FIND_USER, new Object[]{id}, rowMapper);
        jdbcTemplate.query(FIND_POINTS_FOR_USER, new Object[]{id}, subjectRowMapper);
        return usersMap.get(id);

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_POINTS_FOR_USER, id);
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Override
    public Long save(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            return preparedStatement;
        }, holder);
        for (Subject sub : user.getSubjects()) {
            jdbcTemplate.update(SAVE_POINTS_FOR_USER, user.getId(), sub.getId(), sub.getPoint());
        }

        return (Long) holder.getKeys().get("id");
    }

    @Override
    public List<User> findAll() {
        usersMap = new HashMap<Long, User>();
        jdbcTemplate.query(FIND_ALL_USERS, rowMapper);
        jdbcTemplate.query(FIND_ALL_POINTS, subjectRowMapper);
        return new ArrayList<User>(usersMap.values());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(DELETE_POINTS_FOR_USER, user.getId());
        jdbcTemplate.update(UPDATE_USER, user.getName(), user.getSurname(), user.getCountry(), user.getCity(), user.getLogin(), user.getPassword(), user.getId());
        for (Subject sub : user.getSubjects()) {
            jdbcTemplate.update(SAVE_POINTS_FOR_USER, user.getId(), sub.getId(), sub.getPoint());
        }
    }

    @Override
    public User find(String login) {
        usersMap = new HashMap<Long, User>();
        jdbcTemplate.query(FIND_USER_BY_LOGIN, new Object[]{login}, rowMapper);
        jdbcTemplate.query(FIND_POINTS_FOR_USER_BY_LOGIN, new Object[]{login}, subjectRowMapper);
        return new ArrayList<User>(usersMap.values()).get(0);
    }

    }
