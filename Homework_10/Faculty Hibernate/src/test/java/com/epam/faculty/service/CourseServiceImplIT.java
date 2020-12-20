package com.epam.faculty.service;

import com.epam.faculty.HibernateConfig;
import com.epam.faculty.dao.CourseDao;
import com.epam.faculty.dto.CourseDto;
import com.epam.faculty.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class CourseServiceImplIT {

    private Course course;
    private CourseDto courseDto;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseService courseService;

    private static final String courseName = "HTML for begginers";
    private static final LocalDateTime dateTime = LocalDateTime.now();
    private static final int durationWeeks = 10;
    private static final int studentCount = 5;
    private static final long userId = 1;
    private static final String status = "not started";
    private static final int price = 1000;


    @Test
    public void testAddCourse() {
        // GIVEN
        courseDto = new CourseDto();
        courseDto.setCourseId(UUID.randomUUID());
        courseDto.setCourseName(courseName);
        courseDto.setDateTime(dateTime);
        courseDto.setDurationWeeks(durationWeeks);
        courseDto.setPrice(price);
        courseDto.setStatus(status);
        courseDto.setStudentCount(studentCount);
        courseDto.setUserId(userId);
        // WHEN
        courseDto = courseService.create(courseDto);
        // THEN
        assertEquals(courseName, courseDto.getCourseName());
    }

    @Test
    public void testGetByName() {
        Course course = courseService.get(courseName);
        assertEquals(price, course.getPrice());
    }
}
