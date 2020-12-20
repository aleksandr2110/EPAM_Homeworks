package com.epam.faculty.dao;

import com.epam.faculty.dto.CourseDto;
import com.epam.faculty.entity.Course;

import java.util.UUID;

public interface CourseDao {

    void save(Course course);
    Course get(String login);
    Course get(UUID courseId);
}
