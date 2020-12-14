package com.faculty.repository.course;

import com.faculty.exception.CrudException;
import com.faculty.model.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Aleksandr on 22.09.2020.
 */
public interface CourseDao {

    Long saveCourse(Course course) throws CrudException;
    void updateCourse(Long courseId, String courseName, LocalDateTime dateTime, Integer duration, Long userId, String status, Integer price) throws CrudException;
    void deleteCourse(Connection connection, Long courseId) throws CrudException; // add conn
    Course extractCourse(Long courseId) throws CrudException;
    Course extractCourse(Connection connection, Long courseId) throws CrudException;
    List<Course> extractTeacherCourses(Long userId) throws CrudException;
    List<Course> sortCourses(String condition) throws CrudException;
    List<Course> extractCourses() throws CrudException;
    List<Course> extractUserCourses(List<Long> listCourseIds, String status) throws CrudException;
    void addStudentByCourseId(Connection connection, Long courseId, Integer newCountStudents) throws CrudException;
}
