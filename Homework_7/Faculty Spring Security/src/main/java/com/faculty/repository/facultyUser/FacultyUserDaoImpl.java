package com.faculty.repository.facultyUser;

import com.faculty.connection.ConnectionUtil;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.FacultyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faculty.exception.ValidationException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacultyUserDaoImpl implements FacultyUserDao { // add another exception

    private static Logger logger = LoggerFactory.getLogger(FacultyUserDaoImpl.class);

    public static final String SQL_SAVE_FACULTY_USER = "INSERT INTO faculty_user (first_name, last_name, login, password, email, telephone, role, user_blocked) " +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
    public static final String SQL_UPDATE_USER = "UPDATE faculty_user SET first_name = ?, last_name = ?, email= ? WHERE login = ? ";
    public static final String SQL_CHANGE_PASSWORD_USER = "UPDATE faculty_user SET password = ? WHERE login = ?";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM faculty_user WHERE login = ?";
    public static final String SQL_ASSIGN_TEACHER_BY_LOGIN = "UPDATE faculty_user SET role = ? WHERE login = ?";
    public static final String SQL_SELECT_ALL_USERS_BY_ROLE = "SELECT * FROM faculty_user WHERE role = ?";
    public static final String SQL_SELECT_USERS_BY_ROLE_FOR_PAGINATION = "SELECT * FROM faculty_user WHERE role=? LIMIT ?, ?";
    public static final String SQL_FIND_USER_BY_USER_ID = "SELECT * FROM faculty_user WHERE user_id = ?";
    public static final String SQL_BLOCK_UNBLOCK_USER= "UPDATE faculty_user SET user_blocked = ? WHERE user_id = ?";

    public FacultyUserDaoImpl() throws ApplicationException {
    }

    // registration of user
    @Override
    public Long registrationUser(FacultyUser user) throws CrudException {
        Long generatedUserId = null;
            try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_FACULTY_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getLogin());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getTelephone());
                preparedStatement.setString(7, "user");
                preparedStatement.setBoolean(8, false);
                if (preparedStatement.executeUpdate() == 0) {
                    logger.error("Exception in FacultyDaoImpl method registrationUser() with parameter: {}", user.toString());
                    throw new SQLException();
                }
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedUserId = generatedKeys.getLong(1);
                        return generatedUserId;
                    }
                }
            } catch(SQLException e){
                throw new CrudException("Server is available now, please try latter");
            }
            return generatedUserId;
    }

    // get user by user login
    @Override
    public FacultyUser getUserByLogin(String login) throws ValidationException {
        FacultyUser user = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next())
                return extractFacultyUser(result);
        }
        catch(SQLException e){
            logger.error("Exception in FacultyDaoImpl method  getUserByLogin() with parameter: {}", login);
            throw new ValidationException("Wrong password or login!");
        }
        return user;
    }

    // get user by user id
    @Override
    public FacultyUser getUserById(Long userId) throws CrudException {
        FacultyUser user = null;
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_USER_ID )) {
            preparedStatement.setLong(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next())
                return extractFacultyUser(result);
        }
        catch(SQLException e){
            logger.error("Exception in FacultyDaoImpl method  getUserById() with parameter: {}", userId);
            throw new CrudException("Server is available now, please try latter");
        }
        return null;
    }

    @Override
    public void updateUser(String firstName, String lastName, String email, String login) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, login);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in FacultyDaoImpl method updateUser() with parameter: {}, {}, {}, {}", firstName, lastName, email, login);
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get users by role
    @Override
    public List<FacultyUser> getUserByRole(String role) throws CrudException {
        List<FacultyUser> facultyUserList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS_BY_ROLE)) {
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
             facultyUserList.add(extractFacultyUser(resultSet));
            }
            return facultyUserList;
        } catch (SQLException e) {
            logger.error("Exception in FacultyDaoImpl method getUserByRole() with parameter: {}", role);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public List<FacultyUser> getUserByRole(String role, Integer currentPage, Integer recordsPerPage) {
        List<FacultyUser> facultyUserList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_ROLE_FOR_PAGINATION)) {
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, currentPage);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                facultyUserList.add(extractFacultyUser(resultSet));
            }
            return facultyUserList;
        } catch (SQLException e) {
            logger.error("Exception in FacultyDaoImpl method  getUserByRole() with parameters: {}, {}, {}", role, currentPage, recordsPerPage);
            e.printStackTrace();
        }
        return facultyUserList;
    }

    // get list of FacultyUser by list of userId
    @Override
    public List<FacultyUser> getUserOnListUserId(List<Long> listUserId) throws CrudException {
        List<FacultyUser> facultyUserList = new ArrayList<>();
        for(Long userId : listUserId) {
            try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_USER_ID)) {
                preparedStatement.setLong(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    facultyUserList.add(extractFacultyUser(resultSet));
                }
            } catch (SQLException e) {
                logger.error("Exception in FacultyDaoImpl method getUserOnListUserId() with parameter: {}", listUserId);
                throw new CrudException("Server is available now, please try latter");
            }
        }
        return facultyUserList;
    }

    // block / unblock FacultyUser
    @Override
    public void blockUnBlockUser(Long userId, Boolean action) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_UNBLOCK_USER)) {
            preparedStatement.setBoolean(1, action);
            preparedStatement.setLong(2, userId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in FacultyUserDaoImpl method blockUnblockUser() with parameters: {}, {}", userId, action);
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // assigning a teacher
    @Override
    public void assignTeacher(String loginForTeacher) throws CrudException {

        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ASSIGN_TEACHER_BY_LOGIN)) {
            preparedStatement.setString(1, "teacher");
            preparedStatement.setString(2, loginForTeacher);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in FacultyDaoImpl method assignTeacher() with parameter: {}", loginForTeacher);
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public void changePassword(String login, String password) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHANGE_PASSWORD_USER)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, login);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in FacultyDaoImpl method changePassword() with parameter: {}, {}", login, password);
                //throw new SQLException(String.format("Error during creating the object: %s", entity));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    private FacultyUser extractFacultyUser(ResultSet result) throws SQLException
    {
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
