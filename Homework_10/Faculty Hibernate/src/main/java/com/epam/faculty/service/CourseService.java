package com.epam.faculty.service;


import com.epam.faculty.dto.CourseDto;
import com.epam.faculty.entity.Course;

import java.util.UUID;

public interface CourseService {

    CourseDto create(CourseDto courseDto);
    Course get(String courseName);
    Course get(UUID registrationId);
}
