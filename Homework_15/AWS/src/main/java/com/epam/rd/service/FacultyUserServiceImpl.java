package com.epam.rd.service;

import com.epam.rd.entity.FacultyUser;
import com.epam.rd.repository.FacultyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public FacultyUser save(FacultyUser entity) {
        return facultyUserRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        facultyUserRepository.deleteById(id);
    }

    @Override
    public int updateTitleById(String firstName, String lastName, Long userId) {
        return facultyUserRepository.updateTitleById(firstName, lastName, userId);
    }
}
