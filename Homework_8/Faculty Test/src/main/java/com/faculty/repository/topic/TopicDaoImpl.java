package com.faculty.repository.topic;

import com.faculty.connection.ConnectionUtil;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.Topic;
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
public class TopicDaoImpl implements TopicDao {

    private static Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);
    public static final String SQL_SAVE_NEW_TOPIC = "INSERT INTO topic (topic_name) VALUES(?)";
    public static final String SQL_UPDATE_TOPIC = "UPDATE topic SET topic_name = ? WHERE topic_id = ?";
    public static final String SQL_DELETE_TOPIC = "DELETE FROM topic WHERE topic_id =?";
    public static final String SQL_SELECT_ALL_TOPICS = "SELECT * FROM topic";
    public static final String SQL_SELECT_ALL_TOPICS_PER_COURSE = "SELECT * FROM topic WHERE topic_id = ?";

    public TopicDaoImpl() throws ApplicationException {
    }

    @Override
    public Long saveTopic(String topicName) throws CrudException {
        Long generatedTopicId = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_NEW_TOPIC, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, topicName);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in TopicDaoImpl method saveTopic() with parameter: {}", topicName);
                throw new SQLException();
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedTopicId = generatedKeys.getLong(1);
                    return generatedTopicId;
                }
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");
        }
        return generatedTopicId;
    }

    @Override
    public void updateTopic(Long topicId, String topicName) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TOPIC)) {
            preparedStatement.setString(1, topicName);
            preparedStatement.setLong(2, topicId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in TopicDaoImpl method updateTopic() with parameters: {}, {}", topicId, topicName);
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");        }
    }

    @Override
    public void deleteTopic(Long topicId) throws CrudException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TOPIC)) {
            preparedStatement.setLong(1, topicId);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Exception in TopicDaoImpl method deleteTopic() with parameter: {}", topicId);
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public List<Topic> listOfTopics() throws CrudException {
        List<Topic> listOfTopics = new ArrayList();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_TOPICS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                listOfTopics.add(getTopic(resultSet));
            }
            return listOfTopics;
        } catch (SQLException e) {
            logger.error("Exception in TopicDaoImpl method listOfTopics() without parameter: ");
            throw new CrudException("Server is available now, please try latter");
        }
    }

    @Override
    public List<Topic> getTopicsPerCourse(List<Long> topicsId) throws CrudException {
        List<Topic> listTopicsPerCourse = new ArrayList();
        for(Long topicId : topicsId) {
            try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_TOPICS_PER_COURSE)) {
                preparedStatement.setLong(1, topicId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    listTopicsPerCourse.add(getTopic(resultSet));
                }
            } catch (SQLException e) {
                logger.error("Exception in TopicDaoImpl method getTopicsPerCourse() with parameter: {}", topicId);
                throw new CrudException("Server is available now, please try latter");
            }
        }
        return listTopicsPerCourse;
    }

    private Topic getTopic(ResultSet result) throws SQLException
    {
        Topic topic = new Topic(result.getLong("topic_id"),
                result.getString("topic_name"));
        return topic;
    }
}

