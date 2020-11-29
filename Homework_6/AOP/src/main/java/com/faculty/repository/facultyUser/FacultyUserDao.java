package com.faculty.repository.facultyUser;

import com.faculty.exception.CrudException;
import com.faculty.model.FacultyUser;
import com.faculty.exception.ValidationException;
import java.util.List;

/**
 * Created by Aleksandr on 21.09.2020.
 */
public interface FacultyUserDao {

    Long registrationUser(FacultyUser user) throws CrudException;
    FacultyUser getUserByLogin(String login) throws ValidationException;
    FacultyUser getUserById(Long userId) throws CrudException;
    void updateUser(String first_name, String last_name, String email, String login) throws CrudException;
    List<FacultyUser> getUserByRole(String role) throws CrudException;
    List<FacultyUser> getUserByRole(String role, Integer currentPage, Integer recordsPerPage);
    List<FacultyUser> getUserOnListUserId(List<Long> userId) throws CrudException;
    void blockUnBlockUser(Long userId, Boolean action) throws CrudException;
    void assignTeacher(String login) throws CrudException ;
    void changePassword(String login, String password);

}
