package com.faculty.service.Impl;

import com.faculty.aspect.ExecutionTime;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.repository.facultyUser.FacultyUserDao;
import com.faculty.service.FacultyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyUserServiceImpl implements FacultyUserService {

    private static Logger logger = LoggerFactory.getLogger(FacultyUserServiceImpl.class);

    private FacultyUserDao facultyDao;

    public FacultyUserServiceImpl() {}

    @Autowired
    public FacultyUserServiceImpl(FacultyUserDao facultyDao) throws ApplicationException {
        this.facultyDao = facultyDao;
    }

    @ExecutionTime
    @Override
    public Long registrationUser(FacultyUser user) throws CrudException {
        return facultyDao.registrationUser(user);
    }

    @ExecutionTime
    @Override
    public FacultyUser getUserByLogin(String login) throws ValidationException {
        return facultyDao.getUserByLogin(login);
    }

    @ExecutionTime
    @Override
    public FacultyUser getUserById(Long userId) throws CrudException {
        return facultyDao.getUserById(userId);
    }

    @ExecutionTime
    @Override
    public void assignTeacher(String login) throws CrudException {
        facultyDao.assignTeacher(login);
    }

    @ExecutionTime
    @Override
    public void updateUser(String first_name, String last_name, String email, String login) throws CrudException {
        facultyDao.updateUser(first_name, last_name, email, login);
    }

    @ExecutionTime
    @Override
    public List<FacultyUser> getUserByRole(String role) throws CrudException {
        return facultyDao.getUserByRole(role);
    }

    @ExecutionTime
    @Override
    public List<FacultyUser> getUserByRole(String role, Integer currentPage, Integer recordsPerPage) {
        return facultyDao.getUserByRole(role, currentPage, recordsPerPage);
    }

    @ExecutionTime
    @Override
    public List<FacultyUser> getUserOnListUserId(List<Long> listUserId) throws CrudException {
        return facultyDao.getUserOnListUserId(listUserId);
    }

    @ExecutionTime
    @Override
    public void changePassword(String login, String password) {
        facultyDao.changePassword(login, password);
    }

    @ExecutionTime
    @Override
    public FacultyUser login(String login) throws ValidationException {
        FacultyUser facultyUser = facultyDao.getUserByLogin(login);
        return facultyUser;
    }


    @Override
    public void blockUnBlockUser(Long userId, Boolean action) throws CrudException {
        facultyDao.blockUnBlockUser(userId, action);
    }
}
