package com.epam.faculty.assembler;


import com.epam.faculty.dto.FacultyUserDto;
import com.epam.faculty.entity.FacultyUser;

public interface FacultyUserAssembler {

    FacultyUser assemble(FacultyUserDto dto);

    FacultyUserDto assemble(FacultyUser entity);
}
