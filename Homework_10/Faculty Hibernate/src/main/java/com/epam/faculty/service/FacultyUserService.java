package com.epam.faculty.service;


import com.epam.faculty.dto.FacultyUserDto;

import java.util.UUID;

public interface FacultyUserService {

    FacultyUserDto create(FacultyUserDto courseDto);
    FacultyUserDto get(String login);
    FacultyUserDto get(UUID userId);
}
