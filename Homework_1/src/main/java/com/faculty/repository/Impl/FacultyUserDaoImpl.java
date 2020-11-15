package com.faculty.repository.Impl;

import com.faculty.exception.CrudException;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.repository.FacultyUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Repository
public class FacultyUserDaoImpl implements FacultyUserDao {

    private static Logger logger = LoggerFactory.getLogger(FacultyUserDaoImpl.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    public static final String SQL_SAVE_FACULTY_USER = "INSERT INTO faculty_user (first_name, last_name, login, password, email, telephone, role, user_blocked) " +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM faculty_user WHERE login = ?";
    public static final String SQL_FIND_USER_BY_USER_ID = "SELECT * FROM faculty_user WHERE user_id = ?";
    public static final String SQL_UPDATE_USER = "UPDATE faculty_user SET first_name = ?, last_name = ?, email= ? WHERE login = ? ";
    public static final String SQL_SELECT_ALL_USERS_BY_ROLE = "SELECT * FROM faculty_user WHERE role = ?";
    public static final String SQL_SELECT_USERS_BY_ROLE_FOR_PAGINATION = "SELECT * FROM faculty_user WHERE role=? LIMIT ?, ?";
    public static final String SQL_BLOCK_UNBLOCK_USER = "UPDATE faculty_user SET user_blocked = ? WHERE user_id = ?";
    public static final String SQL_ASSIGN_TEACHER_BY_LOGIN = "UPDATE faculty_user SET role = ? WHERE login = ?";
    public static final String SQL_CHANGE_PASSWORD_USER = "UPDATE faculty_user SET password = ? WHERE login = ?";

    public FacultyUserDaoImpl() {}

    @Override
    public Long registrationUser(FacultyUser user) throws CrudException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_FACULTY_USER, new String[] {"order_id"});
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getLogin());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getTelephone());
                preparedStatement.setString(7, "user");
                preparedStatement.setBoolean(8, false);
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public FacultyUser getUserByLogin(String login) throws ValidationException {
        FacultyUser user = null;
        Map<String, Object> mapFromFacultyUser = new HashMap<>();
        mapFromFacultyUser =  jdbcTemplate.queryForMap(SQL_FIND_USER_BY_LOGIN, login);
        user = getUser(mapFromFacultyUser);
        return user;
    }

    @Override
    public FacultyUser getUserById(Long userId) throws CrudException {
        FacultyUser user = null;
        Map<String, Object> mapFromFacultyUser = new HashMap<>();
        mapFromFacultyUser = jdbcTemplate.queryForMap(SQL_FIND_USER_BY_USER_ID, userId);
        user = getUser(mapFromFacultyUser);
        return user;
    }

    @Override
    public void updateUser(String firstName, String lastName, String email, String login) throws CrudException {
        jdbcTemplate.update(SQL_UPDATE_USER, firstName, lastName, email, login);
    }

    @Override
    public List<FacultyUser> getUserByRole(String role) throws CrudException {
        List<FacultyUser> facultyUserList = jdbcTemplate.query(SQL_SELECT_ALL_USERS_BY_ROLE, new Object[] {role},
                new FacultyUserRowMapper());
        return facultyUserList;
    }

    @Override
    public List<FacultyUser> getUserByRole(String role, Integer currentPage, Integer recordsPerPage) {
        List<FacultyUser> facultyUserList = jdbcTemplate.query(SQL_SELECT_USERS_BY_ROLE_FOR_PAGINATION, new Object[] {role,
                        currentPage, recordsPerPage},
                new FacultyUserRowMapper());
        return facultyUserList;
    }

    @Override
    public List<FacultyUser> getUserOnListUserId(List<Long> listUserId) throws CrudException {
        List<FacultyUser> facultyUserList = new ArrayList<>();
        for(Long userId : listUserId) {
            Map<String, Object> mapFromFacultyUser = new HashMap<>();
            mapFromFacultyUser = jdbcTemplate.queryForMap(SQL_FIND_USER_BY_USER_ID, userId);
            FacultyUser user = getUser(mapFromFacultyUser);
            /*
            FacultyUser user = new FacultyUser((Long) mapFromFacultyUser.get("course_id"), (String) mapFromFacultyUser.get("first_name"),
                    (String) mapFromFacultyUser.get("last_name"),  (String) mapFromFacultyUser.get("login"),
                    (String) mapFromFacultyUser.get("password"), (String) mapFromFacultyUser.get("email"),
                    (String) mapFromFacultyUser.get("telephone"), (String) mapFromFacultyUser.get("role"),
                    (Boolean) mapFromFacultyUser.get("user_blocked")); */
            facultyUserList.add(user);
        }
        return facultyUserList;
    }

    @Override
    public void blockUnBlockUser(Long userId, Boolean action) throws CrudException {
        jdbcTemplate.update(SQL_BLOCK_UNBLOCK_USER, userId,  action);
    }

    @Override
    public void assignTeacher(String login) throws CrudException {
        jdbcTemplate.update(SQL_ASSIGN_TEACHER_BY_LOGIN, login);
    }

    @Override
    public void changePassword(String login, String password) {
        jdbcTemplate.update(SQL_CHANGE_PASSWORD_USER, login, password);
    }

    private class FacultyUserRowMapper implements RowMapper<FacultyUser> {

        @Override
        public FacultyUser mapRow(ResultSet result, int i) throws SQLException {
            FacultyUser user = new FacultyUser(result.getInt("user_id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("login"),
                    result.getString("password"),
                    result.getString("email"),
                    result.getString("telephone"),
                    result.getString("role"),
                    result.getBoolean("user_blocked"));
            return user;
        }
    }

    private FacultyUser getUser(Map<String, Object> mapFromFacultyUser) {
        FacultyUser user = new FacultyUser((Long) mapFromFacultyUser.get("course_id"), (String) mapFromFacultyUser.get("first_name"),
                (String) mapFromFacultyUser.get("last_name"),  (String) mapFromFacultyUser.get("login"),
                (String) mapFromFacultyUser.get("password"), (String) mapFromFacultyUser.get("email"),
                (String) mapFromFacultyUser.get("telephone"), (String) mapFromFacultyUser.get("role"),
                (Boolean) mapFromFacultyUser.get("user_blocked"));
        return user;
    }
}
