package com.service;

import com.entity.FacultyUser;

import java.util.List;

/**
 * Created by Aleksandr on 24.11.2020.
 */
public interface FacultyUserService {

    FacultyUser findFacultyUserByUserId(Long facultyUserId);
    List<FacultyUser> findAll();
    List<FacultyUser> findFacultyUserByRole(String role);
    List<FacultyUser> findByRoleOrderByTelephone(String role);
}
