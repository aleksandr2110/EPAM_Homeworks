package com.faculty.repository.courseTopic;

import com.faculty.connection.ConnectionUtil;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.CourseTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseTopicDaoImpl implements CourseTopicDao {

    private static Logger logger = LoggerFactory.getLogger(CourseTopicDaoImpl.class);

    public static final String SQL_SAVE_COURSE_TOPICS = "INSERT INTO course_topic (course_id, topic_id) VALUES(?, ?)";
    public static final String SQL_GET_ALL_COURSE_TOPIC = "SELECT * FROM course_topic";
    public static final String SQL_GET_ALL_COURSE_TOPIC_BY_ID = "SELECT * FROM course_topic WHERE course_id = ?";
    public static final String SQL_DELETE_TOPIC_FROM_COURSE_TOPIC = "DELETE FROM course_topic WHERE topic_id=? AND course_id=?";
    public static final String SQL_DELETE_TOPICS_BY_COURSE_ID = "DELETE FROM course_topic WHERE course_id=?"; //
    public static final String SQL_DELETE_COURSE_TOPIC_BY_COURSE_ID = "DELETE FROM course_topic WHERE course_id=?";
    public static final String SQL_SELECT_COURSE_TOPIC_BY_TOPIC_ID = "SELECT * FROM course_topic WHERE topic_id = ?";
    public static final String SQL_DELETE_COURSE_ON_ID = "DELETE FROM course WHERE course_id =?";


    public CourseTopicDaoImpl() throws ApplicationException {
    }

    // save list of Topic id with courseId
    @Override
    public void saveCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_COURSE_TOPICS)) {
            for(Long topicId : topicsId)
            {
                preparedStatement.setLong(1, courseId);
                preparedStatement.setLong(2, topicId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch(SQLException e){
            logger.error("Exception in CourseTopicDaoImpl method saveCourseTopic() with parameters: {}, ", courseId, topicsId.toString());
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // update CourseTopic
    @Override
    public void updateCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TOPICS_BY_COURSE_ID)) {
            preparedStatement.setLong(1, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseTopicDaoImpl method updateCourseTopic() with parameters: {}, {} ", courseId, topicsId.toString());
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // delete from CourseTopic on courseId
    @Override
    public void deleteCourseTopicByCourseId(Connection connection, Long courseId) throws CrudException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE_TOPIC_BY_COURSE_ID)) {
            preparedStatement.setLong(1, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseTopicDaoImpl method deleteCourseTopicId() with parameter: {}", courseId);
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // delete certain topic from CourseTopic
    @Override
    public void deleteTopicFromCourseTopic(Long courseId, Long topicId) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TOPIC_FROM_COURSE_TOPIC)) {
            preparedStatement.setLong(1, topicId);
            preparedStatement.setLong(2, courseId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in CourseTopicDaoImpl method deleteTopicFromCourseTopic() with parameters: {} ", topicId);
                throw new SQLException();
            }
        } catch(SQLException e){
            throw new CrudException("Server is available now, please try latter");
        }
    }

    // get list of CourseTopic by courseId
    @Override
    public List<CourseTopic> getListTopicById(Long courseId) throws CrudException {
        List<CourseTopic> listCourseTopic = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_COURSE_TOPIC_BY_ID)) {
            preparedStatement.setLong(1, courseId);
            ResultSet result =  preparedStatement.executeQuery();
            while(result.next()) {
                listCourseTopic.add(getCourseTopic(result));
            }
            return listCourseTopic;
        } catch(SQLException e){
            logger.error("Exception in CourseTopicDaoImpl method getListTopicById() with parameter: {}", courseId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public List<CourseTopic> getListCoursesByTopicId(Long topicId) throws CrudException {
        List<CourseTopic> listCourseTopic = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_TOPIC_BY_TOPIC_ID)) {
            preparedStatement.setLong(1, topicId);
            ResultSet result =  preparedStatement.executeQuery();
            while(result.next()) {
                listCourseTopic.add(getCourseTopic(result));
            }
            return listCourseTopic;
        } catch(SQLException e){
            logger.error("Exception in CourseTopicDaoImpl method getListCoursesByTopicId() with parameter: {}", topicId);
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public List<CourseTopic> getListTopic() throws CrudException {
        List<CourseTopic> listCourseTopic = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_COURSE_TOPIC)) {
            ResultSet result =  preparedStatement.executeQuery();
            while(result.next()) {
                listCourseTopic.add(getCourseTopic(result));
            }
            return listCourseTopic;
        } catch(SQLException e){
            logger.error("Exception in CourseTopicDaoImpl method getListTopic() without parameter: ");
            throw new CrudException("Server is available now, please try latter");
        }
    }

    private CourseTopic getCourseTopic(ResultSet result) throws SQLException
    {
        CourseTopic courseTopic = new CourseTopic(result.getLong("course_topic_id"),
                result.getLong("course_id"),
                result.getLong("topic_id"));
        return courseTopic;
    }
}
