package com.faculty.repository.Impl;

import com.faculty.exception.CrudException;
import com.faculty.model.Registration;
import com.faculty.repository.RegistrationDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Aleksandr on 13.11.2020.
 */
@Repository
public class RegistrationDaoImpl implements RegistrationDao {

    private static Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    public static final String SQL_REGISTRATION_ON_COURSE = "INSERT INTO registration (course_id, user_id, approve) " +
            " values(?, ?, ?) ";
    public static final String SQL_APPROVE_REGISTRATION_ON_COURSE = "UPDATE registration SET approve = ? WHERE course_id = ? AND user_id = ?";
    public static final String SQL_DELETE_REGISTRATION_ON_ID = "DELETE FROM registration WHERE registration_id =?";
    public static final String SQL_GET_REGISTRATIONS_LIST_ON_USER_ID = "SELECT * FROM registration WHERE user_id =?";
    public static final String SQL_GET_REGISTRATION_ON_COURSE_USER_ID = "SELECT * FROM registration WHERE course_id=? AND user_id=?";
    public static final String SQL_GET_LIST_ACTION_APPROVE_STUDENTS = "SELECT * FROM registration WHERE approve = ?";
    public static final String SQL_GET_LIST_REGISTRATION_ON_COURSE_ID = "SELECT * FROM registration WHERE course_id =? AND approve=?";
    public static final String SQL_EVALUATE_USER = "UPDATE registration SET student_mark = ? WHERE user_id = ? AND course_id = ?";

    public RegistrationDaoImpl() {}

    @Override
    public Long registrationOnCourse(Long courseId, Long userId, Boolean approve) throws CrudException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTRATION_ON_COURSE, new String[] {"registration_id"});
                preparedStatement.setLong(1, courseId);
                preparedStatement.setLong(2, userId);
                preparedStatement.setBoolean(3, approve);
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void approveRegistrationOnCourse(Connection connection, Long courseId, Long userId, Boolean approve) throws CrudException {
        jdbcTemplate.update(SQL_APPROVE_REGISTRATION_ON_COURSE, courseId, userId, approve);
    }

    @Override
    public void deleteRegistration(Long registrationId) throws CrudException {
        jdbcTemplate.update(SQL_DELETE_REGISTRATION_ON_ID, registrationId);
    }

    @Override
    public List<Registration> getListRegistrationOfUser(Long userId) throws CrudException {
        List<Registration> registrationList = jdbcTemplate.query(SQL_GET_REGISTRATIONS_LIST_ON_USER_ID, new Object[] {userId},
                new RegistrationRowMapper());
        return registrationList;
    }

    @Override
    public Registration getRegistrationByCourseUserId(Long courseId, Long userId) throws CrudException {
        Registration registration = null;
        Map<String, Object> mapFromRegistration = new HashMap<>();
        mapFromRegistration = jdbcTemplate.queryForMap(SQL_GET_REGISTRATION_ON_COURSE_USER_ID, courseId, userId);
        registration = getRegistration(mapFromRegistration);
        return registration;
    }

    @Override
    public List<Registration> getListActionApprove(Boolean approve) throws CrudException {
        List<Registration> registrationList = jdbcTemplate.query(SQL_GET_LIST_ACTION_APPROVE_STUDENTS, new Object[] {approve},
                new RegistrationRowMapper());
        return registrationList;
    }

    @Override
    public List<Registration> getListRegistrationOnCourseId(Long courseId) throws CrudException {
        List<Registration> registrationList = jdbcTemplate.query(SQL_GET_LIST_REGISTRATION_ON_COURSE_ID, new Object[] {courseId},
                new RegistrationRowMapper());
        return null;
    }

    @Override
    public void evaluateUser(Integer userMark, Long userId, Long courseId) throws CrudException {
        jdbcTemplate.update(SQL_EVALUATE_USER, userId, courseId);
    }

    private class RegistrationRowMapper implements RowMapper<Registration> {

        @Override
        public Registration mapRow(ResultSet result, int i) throws SQLException {
            Registration registration = new Registration(result.getLong("registration_id"),
                    result.getLong("course_id"),
                    result.getLong("user_id"),
                    result.getInt("student_mark"),
                    result.getBoolean("approve"));
            return registration;
        }
    }

    private Registration getRegistration(Map<String, Object> mapFromFacultyUser) {
        Registration registration = new Registration((long) mapFromFacultyUser.get("registration_id"),
                (long) mapFromFacultyUser.get("course_id"), (long) mapFromFacultyUser.get("user_id"),
                (int) mapFromFacultyUser.get("student_mark"), (boolean) mapFromFacultyUser.get("approve"));
        return registration;
    }
}
