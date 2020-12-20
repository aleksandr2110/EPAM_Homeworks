package com.epam.faculty.assembler.Impl;

import com.epam.faculty.assembler.FacultyUserAssembler;
import com.epam.faculty.dto.FacultyUserDto;
import com.epam.faculty.entity.FacultyUser;
import org.springframework.stereotype.Component;

@Component
public class FacultyUserAssemblerImpl implements FacultyUserAssembler {

    @Override
    public FacultyUser assemble(FacultyUserDto facultyUserDto) {
        FacultyUser facultyUser = new FacultyUser();
        facultyUser.setUserId(facultyUserDto.getUserId());
        facultyUser.setFirstName(facultyUserDto.getFirstName());
        facultyUser.setLastName(facultyUserDto.getLastName());
        facultyUser.setLogin(facultyUserDto.getLogin());
        facultyUser.setPassword(facultyUserDto.getPassword());
        facultyUser.setRole(facultyUserDto.getRole());
        facultyUser.setTelephone(facultyUserDto.getTelephone());
        facultyUser.setEmail(facultyUserDto.getEmail());
        facultyUserDto.setUserBlocked(facultyUserDto.isUserBlocked());
        return facultyUser;
    }

    @Override
    public FacultyUserDto assemble(FacultyUser facultyUser) {
        FacultyUserDto fudto = new FacultyUserDto();
        fudto.setUserId(facultyUser.getUserId());
        fudto.setFirstName(facultyUser.getFirstName());
        fudto.setLastName(facultyUser.getLastName());
        fudto.setLogin(facultyUser.getLogin());
        fudto.setPassword(facultyUser.getPassword());
        fudto.setTelephone(facultyUser.getTelephone());
        fudto.setRole(facultyUser.getRole());
        fudto.setEmail(facultyUser.getEmail());
        fudto.setUserBlocked(facultyUser.isUserBlocked());
        return fudto;
    }
}
