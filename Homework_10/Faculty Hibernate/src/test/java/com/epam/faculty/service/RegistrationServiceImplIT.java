package com.epam.faculty.service;


import com.epam.faculty.HibernateConfig;
import com.epam.faculty.assembler.FacultyUserAssembler;
import com.epam.faculty.assembler.RegistrationAssembler;
import com.epam.faculty.dto.FacultyUserDto;
import com.epam.faculty.dto.RegistrationDto;
import com.epam.faculty.entity.Course;
import com.epam.faculty.entity.Registration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class RegistrationServiceImplIT {

    private Registration registration;
    private RegistrationDto registrationDto;
    private static final String courseName = "HTML for begginers";
    private static final String login = "proshenko";
    private static final int studentMark = 80;
    private static final boolean approve = false;

    @Autowired
    private FacultyUserService facultyUserService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private FacultyUserAssembler facultyUserAssembler;

    @Autowired
    private RegistrationAssembler registrationAssembler;

    @Test
    public void addRegistration() {
        Course course = courseService.get(courseName);
        FacultyUserDto facultyUserDto = facultyUserService.get(login);
        registration = new Registration();
        registration.setRegistrationId(UUID.randomUUID());
        registration.setCourseId(course.getCourseId());
        registration.setUserId(facultyUserDto.getUserId());
        registration.setApprove(approve);
        registration.setStudentMark(studentMark);
        registration.setFacultyUser(facultyUserAssembler.assemble(facultyUserDto));
        registration.setCourse(course);
        RegistrationDto registrationDto = registrationAssembler.assemble(registration);
        RegistrationDto regDto = registrationService.create(registrationDto);
        assertEquals(studentMark, regDto.getStudentMark());
    }
}
