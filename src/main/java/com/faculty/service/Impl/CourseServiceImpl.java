package com.faculty.service.Impl;

import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.repository.CourseDao;
import com.faculty.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Service
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    public CourseServiceImpl() {}

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Long saveCourse(Course course) throws CrudException {
        return courseDao.saveCourse(course);
    }

    @Override
    public void updateCourse(Long courseId, String courseName, LocalDateTime dateTime, Integer duration, Long userId,
                             String status, Integer price) throws CrudException
    {
        courseDao.updateCourse(courseId, courseName, dateTime, duration, userId, status, price);
    }

    @Override
    public void deleteCourse(Connection connection, Long courseId) throws CrudException
    {
        courseDao.deleteCourse(connection, courseId);
    }

    @Override
    public Course extractCourse(Long courseId) throws CrudException
    {
        return courseDao.extractCourse(courseId);
    }

    @Override
    public Course extractCourse(Connection connection, Long courseId) throws CrudException
    {
        return courseDao.extractCourse(courseId);
    }

    @Override
    public List<Course> extractTeacherCourses(Long userId) throws CrudException {
        return courseDao.extractTeacherCourses(userId);
    }

    @Override
    public List<Course> extractUserCourses(List<Long> listCourseIds, String status) throws CrudException {
        return courseDao.extractUserCourses(listCourseIds, status);
    }

    @Override
    public List<Course> extractCourses() throws CrudException
    {
        return courseDao.extractCourses();
    }


    @Override
    public List<Course> sortCourses(String condition) throws CrudException {
        return courseDao.sortCourses(condition);
    }

    @Override
    public void addStudentByCourseId(Connection connection, Long courseId, Integer newCountStudents) throws CrudException {
        courseDao.addStudentByCourseId(connection, courseId, newCountStudents);
    }
}
