package com.epam.faculty.service;

import com.epam.faculty.assembler.CourseAssembler;
import com.epam.faculty.dao.CourseDao;
import com.epam.faculty.dto.CourseDto;
import com.epam.faculty.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseAssembler courseAssembler;

    @Override
    @Transactional
    public CourseDto create(CourseDto csDto) {
        Course course = courseAssembler.assemble(csDto);
        courseDao.save(course);
        CourseDto courseDto = courseAssembler.assemble(course);
        return courseDto;
    }

    @Override
    @Transactional
    public Course get(String courseName) {
        Course course = courseDao.get(courseName);
        return course;
        //return courseAssembler.assemble(course);
    }

    @Override
    @Transactional
    public Course get(UUID registrationId) {
        Course course = courseDao.get(registrationId);
        return course;
        //return courseAssembler.assemble(course);
    }
}
