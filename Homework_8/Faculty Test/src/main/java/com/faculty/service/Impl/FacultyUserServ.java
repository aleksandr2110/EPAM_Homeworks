package com.faculty.service.Impl;

import com.faculty.connection.ConnectionUtil;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.repository.facultyUser.FacultyUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service("faculty")
public class FacultyUserServ {

    private FacultyUserDao facultyDao;

    public FacultyUserServ() {}

    @Autowired
    public FacultyUserServ(FacultyUserDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    public FacultyUser getUserByLogin(String login) throws ValidationException {
        return facultyDao.getUserByLogin(login);
    }
}
