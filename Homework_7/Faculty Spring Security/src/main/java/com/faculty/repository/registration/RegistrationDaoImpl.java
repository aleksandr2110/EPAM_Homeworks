package com.faculty.repository.registration;

import com.faculty.connection.ConnectionUtil;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

    private static Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
    public static final String SQL_REGISTRATION_ON_COURSE = "INSERT INTO registration (course_id, user_id, approve) " +
            " values(?, ?, ?) ";
    public static final String SQL_APPROVE_REGISTRATION_ON_COURSE = "UPDATE registration SET approve = ? WHERE course_id = ? AND user_id = ?";
    public static final String SQL_DELETE_REGISTRATION_ON_ID = "DELETE FROM registration WHERE registration_id =?";
    public static final String SQL_GET_REGISTRATIONS_LIST_ON_USER_ID = "SELECT * FROM registration WHERE user_id =?";
    public static final String SQL_GET_REGISTRATION_ON_COURSE_USER_ID = "SELECT * FROM registration WHERE course_id=? AND user_id=?";
    public static final String SQL_GET_LIST_ACTION_APPROVE_STUDENTS = "SELECT * FROM registration WHERE approve = ?";
    public static final String SQL_GET_LIST_REGISTRATION_ON_COURSE_ID = "SELECT * FROM registration WHERE course_id =? AND approve=?";
    public static final String SQL_EVALUATE_USER = "UPDATE registration SET student_mark = ? WHERE user_id = ? AND course_id = ?";

    public RegistrationDaoImpl() throws ApplicationException {
    }

    // request for attendance on course, must be approved by admin
    @Override
    public Long registrationOnCourse(Long courseId, Long userId, Boolean approve) throws CrudException {
        Long generatedRegistrationId = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTRATION_ON_COURSE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setBoolean(3, approve);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in RegistrationDaoImpl method registrationOnCourse() with parameters: {}, {}, {}",
                        courseId, userId, approve);
                throw new SQLException();
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedRegistrationId = generatedKeys.getLong(1);
                    return generatedRegistrationId;
                }
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");
        }
        return generatedRegistrationId;
    }

    // attendance on course approved by admin
    @Override
    public void approveRegistrationOnCourse(Connection connection, Long courseId, Long userId, Boolean approve) throws CrudException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_REGISTRATION_ON_COURSE)) {
            preparedStatement.setBoolean(1, approve);
            preparedStatement.setLong(2, courseId);
            preparedStatement.setLong(3, userId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in RegistrationDaoImpl method approveRegistrationOnCourse() with parameters: {}, {}, {}",
                        courseId, userId, approve);
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // delete registration
    @Override
    public void deleteRegistration(Long registrationId) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_REGISTRATION_ON_ID)) {
            preparedStatement.setLong(1, registrationId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in RegistrationDaoImpl method deleteRegistration() with parameters: {}", registrationId);
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public List<Registration> getListRegistrationOfUser(Long userId) throws CrudException  {
        List<Registration> listRegistration = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_REGISTRATIONS_LIST_ON_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listRegistration.add(getRegistration(resultSet));
            }
            return listRegistration;
        } catch (SQLException e) {
            logger.error("Exception in RegistrationDaoImpl method getListRegistrationOfUser() with parameter: {}", userId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get registration on courseId and userId
    @Override
    public Registration getRegistrationByCourseUserId(Long courseId, Long userId) throws CrudException  {
        Registration registration = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_REGISTRATION_ON_COURSE_USER_ID)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                registration = getRegistration(resultSet);
            }
            return registration;
        } catch (SQLException e) {
            logger.error("Exception in RegistrationDaoImpl method getRegistrationByCourseId() with parameter: {}", courseId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get list approved / not approved students
    @Override
    public List<Registration> getListActionApprove(Boolean approve) throws CrudException  {
        List<Registration> listRegistrationActionApprove = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST_ACTION_APPROVE_STUDENTS)) {
            preparedStatement.setBoolean(1, approve);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listRegistrationActionApprove.add(getRegistration(resultSet));
            }
            return listRegistrationActionApprove;
        } catch (SQLException e) {
            logger.error("Exception in RegistrationDaoImpl method getListRegistrationOfUser() with parameter: {}", approve);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get list registration by courseId
    @Override
    public List<Registration> getListRegistrationOnCourseId(Long courseId) throws CrudException  {
        List<Registration> registration = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST_REGISTRATION_ON_COURSE_ID)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setBoolean(2, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                registration.add(getRegistration(resultSet));
            }
            return registration;
        } catch (SQLException e) {
            logger.error("Exception in RegistrationDaoImpl method getListRegistrationOnCourseId() with parameter: {}, {}", courseId, true);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // evaluate student
    @Override
    public void evaluateUser(Integer userMark, Long userId, Long courseId) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_EVALUATE_USER)) {
            preparedStatement.setInt(1, userMark);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in RegistrationDaoImpl method evaluateUser() with parameters: {}, {}, {}",
                        userMark, userId, courseId);
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");
        }
    }

    private Registration getRegistration(ResultSet result) throws SQLException {
        Registration registration = new Registration(result.getLong("registration_id"),
                result.getLong("course_id"),
                result.getLong("user_id"),
                result.getInt("student_mark"),
                result.getBoolean("approve"));
        return registration;
    }
}
