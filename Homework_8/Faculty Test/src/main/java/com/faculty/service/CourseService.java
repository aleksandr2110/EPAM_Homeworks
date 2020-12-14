package com.faculty.service;

import com.faculty.exception.CrudException;
import com.faculty.model.Course;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseService {

    Long saveCourse(Course course) throws CrudException;

    void updateCourse(Long courseId, String courseName, LocalDateTime dateTime, Integer duration, Long userId, String status, Integer price) throws CrudException;

    void deleteCourse(Connection connection, Long courseId) throws CrudException;

    Course extractCourse(Long courseId) throws CrudException;

    Course extractCourse(Connection connection, Long courseId) throws CrudException;

    List<Course> extractTeacherCourses(Long userId) throws CrudException;

    List<Course> extractUserCourses(List<Long> listCourseIds, String status) throws CrudException;

    List<Course> extractCourses() throws CrudException;

    List<Course> sortCourses(String condition) throws CrudException;

    void addStudentByCourseId(Connection connection, Long courseId, Integer newCountStudents) throws CrudException;
}
