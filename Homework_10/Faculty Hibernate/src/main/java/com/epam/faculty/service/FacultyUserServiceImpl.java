package com.epam.faculty.service;

import com.epam.faculty.assembler.FacultyUserAssembler;
import com.epam.faculty.dao.FacultyUserDao;
import com.epam.faculty.dto.FacultyUserDto;
import com.epam.faculty.entity.FacultyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FacultyUserServiceImpl implements FacultyUserService {

    @Autowired
    private FacultyUserDao facultyUserDao;

    @Autowired
    private FacultyUserAssembler facultyUserAssembler;

    public FacultyUserServiceImpl() {}

    @Override
    @Transactional
    public FacultyUserDto create(FacultyUserDto FUDto) {
        FacultyUser facultyUser = facultyUserAssembler.assemble(FUDto);
        facultyUserDao.save(facultyUser);
        FacultyUserDto facultyUserDto = facultyUserAssembler.assemble(facultyUser);
        return facultyUserDto;
    }

    @Override
    @Transactional
    public FacultyUserDto get(String login) {
        FacultyUser facultyUser = facultyUserDao.get(login);
        return facultyUserAssembler.assemble(facultyUser);
    }

    @Override
    @Transactional
    public FacultyUserDto get(UUID userId) {
        FacultyUser facultyUser = facultyUserDao.get(userId);
        return facultyUserAssembler.assemble(facultyUser);
    }
}
