package ru.itis.inform.dao.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.inform.dao.SubjectsDAO;
import ru.itis.inform.model.Subject;
import ru.itis.inform.model.builder.SubjectBuilder;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class SubjectsDAOImpl implements SubjectsDAO {

    private JdbcTemplate jdbcTemplate;

    // language=SQL
    private final static String FIND_SUBJECT = "SELECT * FROM subjects WHERE subjects.id = ?";
    // language=SQL
    private final static String FIND_ALL_SUBJECTS = "SELECT * FROM subjects";
    // language=SQL
    private final static String DELETE_SUBJECT = "DELETE  FROM subjects WHERE subjects.id = ?";
    // language=SQL
    private final static String UPDATE_SUBJECT = "UPDATE  subjects SET subjects.sub_name = ? WHERE id = ?";
    // language=SQL
    private final static String SAVE_SUBJECT = "INSERT INTO subjects (id, sub_name) VALUES (?,?)";


    private RowMapper<Subject> rowMapper = new RowMapper<Subject>() {
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {

            Subject res = new SubjectBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("sub_name"))
                    .createSubject();

            return res;
        }
    };

    @Autowired
    public SubjectsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Subject find(Long id) {
        return jdbcTemplate.queryForObject(FIND_SUBJECT, new Object[]{id}, rowMapper);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_SUBJECT, id);
    }

    @Override
    public Long save(Subject subject) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SAVE_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, subject.getId());
            preparedStatement.setString(2, subject.getName());
            return preparedStatement;
        }, holder);

        return (Long) holder.getKeys().get("id");
    }

    @Override
    public List<Subject> findAll() {
        return jdbcTemplate.query(FIND_ALL_SUBJECTS, rowMapper);
    }

    @Override
    public void update(Subject subject) {
        jdbcTemplate.update(UPDATE_SUBJECT, subject.getName(), subject.getId());
    }
}
