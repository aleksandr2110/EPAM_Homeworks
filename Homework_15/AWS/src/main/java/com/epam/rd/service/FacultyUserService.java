package com.epam.rd.service;

import com.epam.rd.entity.FacultyUser;

import java.util.List;


public interface FacultyUserService {

    FacultyUser findFacultyUserByUserId(Long userId);
    List<FacultyUser> findAll();
    List<FacultyUser> findFacultyUserByRole(String role);
    List<FacultyUser> findByRoleOrderByTelephone(String role);
    FacultyUser save(FacultyUser entity);
    void deleteById(Long id);
    int updateTitleById(String firstName, String lastName, Long userId);
}
