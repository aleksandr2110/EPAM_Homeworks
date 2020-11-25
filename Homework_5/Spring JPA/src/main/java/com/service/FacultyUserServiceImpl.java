package com.service;

import com.entity.FacultyUser;
import com.repository.FacultyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aleksandr on 24.11.2020.
 */
@Service
public class FacultyUserServiceImpl implements FacultyUserService {

    @Autowired
    private FacultyUserRepository facultyUserRepository;

    @Override
    public FacultyUser findFacultyUserByUserId(Long userId) {
        return facultyUserRepository.findFacultyUserByUserId(userId);
    }

    @Override
    public List<FacultyUser> findAll() {
        return facultyUserRepository.findAll();
    }

    @Override
    public List<FacultyUser> findFacultyUserByRole(String role) {
        return facultyUserRepository.findFacultyUserByRole(role);
    }

    @Override
    public List<FacultyUser> findByRoleOrderByTelephone(String role) {
        return facultyUserRepository.findByRoleOrderByTelephone(role);
    }
}
