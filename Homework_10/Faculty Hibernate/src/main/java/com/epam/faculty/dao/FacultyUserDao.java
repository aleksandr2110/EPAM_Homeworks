package com.epam.faculty.dao;

import com.epam.faculty.entity.FacultyUser;

import java.util.UUID;

public interface FacultyUserDao {

    void save(FacultyUser facultyUser);
    FacultyUser get(String login);
    FacultyUser get(UUID facultyUsrId);
}
