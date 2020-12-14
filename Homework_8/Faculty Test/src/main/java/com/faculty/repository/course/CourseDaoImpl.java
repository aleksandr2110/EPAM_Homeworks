package com.faculty.repository.course;

import com.faculty.connection.ConnectionUtil;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private static Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    public static final String SQL_SAVE_COURSE = "INSERT INTO course (course_name, start_time, duration_weeks, student_count, user_id, status, price)"
             + " VALUES(?, ?, ?, ?, ?, ?, ?) ";
    public static final String SQL_UPDATE_COURSE_ON_ID = "UPDATE course SET course_name = ?, start_time = ?, duration_weeks = ?, "
    + "user_id = ?, status = ?, price = ? WHERE course_id = ?";
    public static final String SQL_DELETE_COURSE_ON_ID = "DELETE FROM course WHERE course_id =?";
    public static final String SQL_EXTRACT_COURSE_BY_ID = "SELECT * FROM course WHERE course_id = ?";
    public static final String SQL_EXTRACT_All_COURSES = "SELECT * FROM course";
    public static final String SQL_SORT_COURSES = "SELECT * FROM course ORDER BY course_name ";
    public static final String SQL_EXTRACT_COURSES_BY_COURSE_STATUS = "SELECT * FROM course WHERE course_id = ? AND status = ?";
    public static final String SQL_ADD_STUDENT_BY_COURSE_ID = "UPDATE course SET student_count = ? WHERE course_id = ?";
    public static final String SQL_EXTRACT_ALL_COURSES_TEACHER = "SELECT * FROM course WHERE user_id = ?";

    public CourseDaoImpl() throws ApplicationException {
    }

    // save new course
    @Override
    public Long saveCourse(Course course) throws CrudException   {
        Long generatedCourseId = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_COURSE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setObject(2, course.getDateTime());
            preparedStatement.setInt(3, course.getDurationWeeks());
            preparedStatement.setLong(4, 0);
            preparedStatement.setLong(5, course.getUserId());
            preparedStatement.setString(6, course.getStatus());
            preparedStatement.setInt(7, course.getPrice());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseDaoImpl method saveCourse() with parameter: {} ExecuteUpdate didn't executed", course.toString());
                throw new SQLException();
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedCourseId = generatedKeys.getLong(1);
                    return generatedCourseId;
                }
            }
        }
        catch(SQLException e){
           throw new CrudException("Server is available now, please try latter");
        }
        return generatedCourseId;
    }

    // update course
    @Override
    public void updateCourse(Long courseId, String courseName, LocalDateTime dateTime, Integer duration, Long userId, String status, Integer price)
            throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COURSE_ON_ID)) {
            preparedStatement.setString(1, courseName);
            preparedStatement.setObject(2, dateTime);
            preparedStatement.setInt(3, duration);
            preparedStatement.setLong(4, userId);
            preparedStatement.setString(5, status);
            preparedStatement.setInt(6, price);
            preparedStatement.setLong(7, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseDaoImpl method updateCourse() with parameters: {}, {}, {}, {}, {}, {}", courseId, courseName, dateTime, price);
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // delete course by course id
    @Override
    public void deleteCourse(Connection connection, Long courseId) throws CrudException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE_ON_ID)) {
            preparedStatement.setLong(1, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseDaoImpl method deleteCourse() with parameter: {}, ", courseId);
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get course bu course id
    @Override
    public Course extractCourse(Long courseId) throws CrudException  {
        Course course = null;
        try (Connection connection = ConnectionUtil.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXTRACT_COURSE_BY_ID)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                course = getCourse(resultSet);
            }
            return course;
        } catch(SQLException e){
            logger.error("Exception in CourseDaoImpl method extractCourse() with parameter: {}, ", courseId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get course bu course id addition parameter Connection
    @Override
    public Course extractCourse(Connection connection, Long courseId) throws CrudException  {
        Course course = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXTRACT_COURSE_BY_ID)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                course = getCourse(resultSet);
            }
            return course;
        } catch(SQLException e){
            logger.error("Exception in CourseDaoImpl method extractCourse() with parameters: {}, {}, ", connection, courseId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get all courses of teacher
    @Override
    public List<Course> extractTeacherCourses(Long userId) throws CrudException  {
        List<Course> listCourses = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXTRACT_ALL_COURSES_TEACHER)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                listCourses.add(getCourse(resultSet));
            }
            return listCourses;
        } catch(SQLException e){
            logger.error("Exception in CourseDaoImpl method  extractTeacherCourses() with parameter: {}", userId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // sort courses on name, condition has or ASC or DESC
    @Override
    public List<Course> sortCourses(String condition) throws CrudException  {
        List<Course> listCourses = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SORT_COURSES + condition)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                listCourses.add(getCourse(resultSet));
            }
            return listCourses;
        } catch(SQLException e){
            logger.error("Exception in CourseDaoImpl method sortCourses() with parameter: {}", condition);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get all courses of IT company
    @Override
    public List<Course> extractCourses() throws CrudException  {
        List<Course> listCourses = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXTRACT_All_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                listCourses.add(getCourse(resultSet));
            }
            return listCourses;
        } catch(SQLException e){
            logger.error("Exception in CourseDaoImpl method extractCourses() without parameter: ");
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get all courses by list of course id and status
    @Override
    public List<Course> extractUserCourses(List<Long> listCourseIds, String status) throws CrudException  {
        List<Course> listUserCourses = new ArrayList<>();
        for(Long courseId : listCourseIds)
        {
            try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXTRACT_COURSES_BY_COURSE_STATUS)) {
                preparedStatement.setLong(1, courseId);
                preparedStatement.setString(2, status);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    listUserCourses.add(getCourse(resultSet));
                }
            } catch(SQLException e){
                logger.error("Exception in CourseDaoImpl method extractUserCourses() with parameters: {}, {}",
                        listCourseIds.toString(), status);
                throw new CrudException("Server is available now, please try latter");
            }
        }
        return listUserCourses;
    }

    @Override
    public void addStudentByCourseId(Connection connection, Long courseId, Integer newCountStudents) throws CrudException  {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_STUDENT_BY_COURSE_ID)) {
            preparedStatement.setInt(1, newCountStudents);
            preparedStatement.setLong(2, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseDaoImpl method addStudentByCourseId with parameters: {}, {}, {}",
                        courseId, newCountStudents, SQL_ADD_STUDENT_BY_COURSE_ID);
                throw new SQLException("Error during creating the object: ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get course by ResultSet
    private Course getCourse(ResultSet resultSet) throws SQLException
    {
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
