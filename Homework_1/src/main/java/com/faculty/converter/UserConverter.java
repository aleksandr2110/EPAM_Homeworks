package com.faculty.converter;

import com.faculty.dto.FacultyUserDto;
import com.faculty.model.FacultyUser;

/**
 * Created by Aleksandr on 13.11.2020.
 */
public class UserConverter {

    public static FacultyUserDto userToUserViewDto(FacultyUser user) {

        FacultyUserDto userViewDto = new FacultyUserDto();
        userViewDto.setUserId(user.getUserId());
        userViewDto.setFirstName(user.getFirstName());
        userViewDto.setLastName(user.getLastName());
        userViewDto.setLogin(user.getLogin());
        userViewDto.setEmail(user.getEmail());
        userViewDto.setRole(user.getRole());
        userViewDto.setTelephone(user.getTelephone());
        userViewDto.setUserBlocked(user.isUserBlocked());
        return userViewDto;
    }
}
