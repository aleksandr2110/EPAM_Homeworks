package com.epam.faculty.service;


import com.epam.faculty.HibernateConfig;
import com.epam.faculty.dao.FacultyUserDao;
import com.epam.faculty.dto.FacultyUserDto;
import com.epam.faculty.entity.FacultyUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class FacultyUserServiceImplIT {

    // execute database.sql
    private static final String firstName = "Andrey";
    private static final String lastName = "Proshenko";
    private static final String login = "proshenko";
    private static final String telephone = "0736723451";
    private static final String password = "hibernatecool";
    private static final String email = "proshenko@epam.com";
    private static final String role = "user";
    private static final boolean userBlocked = false;

    private FacultyUser facultyUser;
    private FacultyUserDto facultyUserDto;

    @Autowired
    private FacultyUserService facultyUserService;

    public FacultyUserServiceImplIT() {}

    @Test
    public void testAddUser() {
        // GIVEN
        facultyUserDto = new FacultyUserDto();
        facultyUserDto.setUserId(UUID.randomUUID());
        facultyUserDto.setFirstName(firstName);
        facultyUserDto.setLastName(lastName);
        facultyUserDto.setLogin(login);
        facultyUserDto.setPassword(password);
        facultyUserDto.setTelephone(telephone);
        facultyUserDto.setUserBlocked(userBlocked);
        facultyUserDto.setEmail(email);
        facultyUserDto.setRole(role);
        // WHEN
        FacultyUserDto facUserDto = facultyUserService.create(facultyUserDto);
        // THEN
        assertEquals(firstName, facUserDto.getFirstName());
    }

    @Test
    public void testGetByLogin() {
        // GIVEN
        // WHEN
        FacultyUserDto facultyUserDto = facultyUserService.get(login);
        // THEN
        assertEquals(login, facultyUserDto.getLogin());
    }

}
