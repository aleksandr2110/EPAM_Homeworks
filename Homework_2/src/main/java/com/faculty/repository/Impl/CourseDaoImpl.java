package com.faculty.repository.Impl;

import com.faculty.bpp.Timed;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.repository.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Aleksandr on 08.11.2020.
 */
@Repository
@Timed
public class CourseDaoImpl implements CourseDao {

    @Autowired
    protected DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public static final String SQL_SAVE_COURSE = "INSERT INTO course (course_name, start_time, duration_weeks, student_count, user_id, status, price)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?) ";
    public static final String SQL_UPDATE_COURSE_ON_ID = "UPDATE course SET course_name = ?, start_time = ?, duration_weeks = ?, "
            + "user_id = ?, status = ?, price = ? WHERE course_id = ?";
    public static final String SQL_DELETE_COURSE_ON_ID = "DELETE FROM course WHERE course_id =?";
    public static final String SQL_EXTRACT_COURSE_BY_ID = "SELECT * FROM course WHERE course_id = ?";
    public static final String SQL_SORT_COURSES = "SELECT * FROM course ORDER BY course_name ";
    public static final String SQL_EXTRACT_COURSES_BY_COURSE_STATUS = "SELECT * FROM course WHERE course_id =? AND status =?";
    public static final String SQL_EXTRACT_All_COURSES = "SELECT * FROM course";
    public static final String SQL_ADD_STUDENT_BY_COURSE_ID = "UPDATE course SET student_count =? WHERE course_id =?";
    public static final String SQL_EXTRACT_ALL_COURSES_TEACHER = "SELECT * FROM course WHERE user_id = ?";
// http://zetcode.com/db/jdbctemplate/

    public CourseDaoImpl() {}
    @Override
    public Long saveCourse(Course course) throws CrudException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_COURSE, new String[] {"user_id"});
                    preparedStatement.setString(1, course.getCourseName());
                    preparedStatement.setObject(2, course.getDateTime());
                    preparedStatement.setInt(3, course.getDurationWeeks());
                    preparedStatement.setLong(4, 0);
                    preparedStatement.setLong(5, course.getUserId());
                    preparedStatement.setString(6, course.getStatus());
                    preparedStatement.setInt(7, course.getPrice());
                return preparedStatement;
                }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateCourse(Long courseId, String courseName, LocalDateTime dateTime, Integer duration, Long userId, String status, Integer price) throws CrudException {

        jdbcTemplate.update(SQL_UPDATE_COURSE_ON_ID, courseId, courseName, dateTime, duration, userId, status, price);
    }

    @Override
    public void deleteCourse(Connection connection, Long courseId) throws CrudException {
        jdbcTemplate.update(SQL_DELETE_COURSE_ON_ID, courseId);
    }

    @Override
    public Course extractCourse(Long courseId) throws CrudException {
        Course course = null;
        Map<String, Object> mapFromCourse = new HashMap<>();
        mapFromCourse = jdbcTemplate.queryForMap(SQL_EXTRACT_COURSE_BY_ID, courseId);
        course = new Course((Long) mapFromCourse.get("course_id"), (String) mapFromCourse.get("course_name"),
                ((Timestamp) mapFromCourse.get("start_time")).toLocalDateTime(), (int) mapFromCourse.get("duration_weeks"),
                (int) mapFromCourse.get("student_count"), (Long) mapFromCourse.get("user_id"), (String) mapFromCourse.get("status"),
                (int) mapFromCourse.get("price"));
        return course;
    }

    @Override
    public Course extractCourse(Connection connection, Long courseId) throws CrudException {
        Course course = null;
        Map<String, Object> mapFromCourse = new HashMap<>();
        mapFromCourse = jdbcTemplate.queryForMap(SQL_EXTRACT_COURSE_BY_ID, courseId);
        course = new Course((Long) mapFromCourse.get("course_id"), (String) mapFromCourse.get("course_name"),
                ((Timestamp) mapFromCourse.get("start_time")).toLocalDateTime(), (int) mapFromCourse.get("duration_weeks"),
                (int) mapFromCourse.get("student_count"), (Long) mapFromCourse.get("user_id"), (String) mapFromCourse.get("status"),
                (int) mapFromCourse.get("price"));
        return course;
    }

    @Override
    public List<Course> extractTeacherCourses(Long userId) throws CrudException {
        List<Course> teachersCourses = jdbcTemplate.query(SQL_EXTRACT_ALL_COURSES_TEACHER,
                new Object[] { userId },
                new RowMapper<Course>() {
                    @Override
                    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Course(rs.getLong("course_id"), rs.getString("course_name"),
                                rs.getTimestamp("start_time").toLocalDateTime(), rs.getInt("duration_weeks"),
                                rs.getInt("student_count"), rs.getLong("user_id"), rs.getString("status"),
                                rs.getInt("price"));
                    }
                });
        return teachersCourses;
    }

    @Override
    public List<Course> sortCourses(String condition) throws CrudException {
        return jdbcTemplate.query(SQL_SORT_COURSES + condition, new CourseRowMapper());
    }

    @Override
    public List<Course> extractCourses() throws CrudException {
        List<Course> listCourses = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_EXTRACT_All_COURSES);
        for(Map<String, Object> row : rows){
            long courseId = (Long) row.get("course_id");
            String courseName = (String)row.get("course_name");
            LocalDateTime startTime = ((Timestamp) row.get("start_time")).toLocalDateTime();
            int durationWeeks = (Integer) row.get("duration_weeks");
            int studentCount = (Integer) row.get("student_count");
            long userId = (Long) row.get("user_id");
            String status = (String) row.get("status");
            int price = (Integer) row.get("price");
            listCourses.add(new Course(courseId, courseName, startTime, durationWeeks, studentCount, userId, status, price));
        }
        return listCourses;
    }

    @Override
    public List<Course> extractUserCourses(List<Long> listCourseIds, String status) throws CrudException {
        List<Course> listUserCourses = new ArrayList<>();
        for(Long courseId : listCourseIds) {
            Map<String, Object> mapFromCourse = new HashMap<>();
            mapFromCourse = jdbcTemplate.queryForMap(SQL_EXTRACT_COURSES_BY_COURSE_STATUS, status);
            Course course = new Course((Long) mapFromCourse.get("course_id"), (String) mapFromCourse.get("course_name"),
                    ((Timestamp) mapFromCourse.get("start_time")).toLocalDateTime(), (int) mapFromCourse.get("duration_weeks"),
                    (int) mapFromCourse.get("student_count"), (Long) mapFromCourse.get("user_id"), (String) mapFromCourse.get("status"),
                    (int) mapFromCourse.get("price"));
            listUserCourses.add(course);
        }
        return listUserCourses;
    }

    @Override
    public void addStudentByCourseId(Connection connection, Long courseId, Integer newCountStudents) throws CrudException {
        jdbcTemplate.update(SQL_ADD_STUDENT_BY_COURSE_ID, courseId,  newCountStudents);
    }

    private class CourseRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course(resultSet.getLong("course_id"),
                    resultSet.getString("course_name"),
                    resultSet.getTimestamp("start_time").toLocalDateTime(),
                    resultSet.getInt("duration_weeks"),
                    resultSet.getInt("student_count"),
                    resultSet.getLong("user_id"),
                    resultSet.getString("status"),
                    resultSet.getInt("price"));
            return course;
        }
    }
}
