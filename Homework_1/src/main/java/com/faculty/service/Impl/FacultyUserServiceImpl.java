package com.faculty.service.Impl;

import com.faculty.exception.CrudException;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.repository.FacultyUserDao;
import com.faculty.service.FacultyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Service
public class FacultyUserServiceImpl implements FacultyUserService {

    private FacultyUserDao facultyUserDao;

    public FacultyUserServiceImpl() {}

    @Autowired
    public FacultyUserServiceImpl(FacultyUserDao facultyUserDao) {
        this.facultyUserDao = facultyUserDao;
    }

    @Override
    public Long registrationUser(FacultyUser user) throws CrudException
    {
        return facultyUserDao.registrationUser(user);
    }

    @Override
    public FacultyUser getUserByLogin(String login) throws ValidationException
    {
        return facultyUserDao.getUserByLogin(login);
    }

    @Override
    public FacultyUser getUserById(Long userId) throws CrudException {
        return facultyUserDao.getUserById(userId);
    }

    @Override
    public void assignTeacher(String login) throws CrudException {
        facultyUserDao.assignTeacher(login);
    }

    @Override
    public void updateUser(String first_name, String last_name, String email, String login) throws CrudException {
        facultyUserDao.updateUser(first_name, last_name, email, login);
    }

    @Override
    public List<FacultyUser> getUserByRole(String role) throws CrudException {
        return facultyUserDao.getUserByRole(role);
    }

    @Override
    public List<FacultyUser> getUserByRole(String role, Integer currentPage, Integer recordsPerPage) {
        return facultyUserDao.getUserByRole(role, currentPage, recordsPerPage);
    }

    @Override
    public List<FacultyUser> getUserOnListUserId(List<Long> listUserId) throws CrudException {
        return facultyUserDao.getUserOnListUserId(listUserId);
    }

    @Override
    public void changePassword(String login, String password)
    {
        facultyUserDao.changePassword(login, password);
    }

    @Override
    public FacultyUser login(String login) throws ValidationException {
        FacultyUser facultyUser = facultyUserDao.getUserByLogin(login);
        return facultyUser;
    }


    @Override
    public void blockUnBlockUser(Long userId, Boolean action) throws CrudException  {
        facultyUserDao.blockUnBlockUser(userId, action);
    }
}
