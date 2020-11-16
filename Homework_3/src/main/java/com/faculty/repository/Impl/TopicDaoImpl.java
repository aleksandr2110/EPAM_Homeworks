package com.faculty.repository.Impl;

import com.faculty.bpp.Timed;
import com.faculty.exception.CrudException;
import com.faculty.model.Topic;
import com.faculty.repository.TopicDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Repository
@Timed
public class TopicDaoImpl implements TopicDao {

    private static Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    public static final String SQL_SAVE_NEW_TOPIC = "INSERT INTO topic (topic_name) VALUES(?)";
    public static final String SQL_UPDATE_TOPIC = "UPDATE topic SET topic_name = ? WHERE topic_id = ?";
    public static final String SQL_DELETE_TOPIC = "DELETE FROM topic WHERE topic_id =?";
    public static final String SQL_SELECT_ALL_TOPICS = "SELECT * FROM topic";
    public static final String SQL_SELECT_ALL_TOPICS_PER_COURSE = "SELECT * FROM topic WHERE topic_id = ?";

    public TopicDaoImpl() {}

    @Override
    public Long saveTopic(String topicName) throws CrudException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_NEW_TOPIC, new String[] {"topic_id"});
                preparedStatement.setString(1, topicName);
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateTopic(Long topicId, String topicName) throws CrudException {
        jdbcTemplate.update(SQL_UPDATE_TOPIC, topicId, topicName);
    }

    @Override
    public void deleteTopic(Long topicId) throws CrudException {
        jdbcTemplate.update(SQL_DELETE_TOPIC, topicId);
    }

    @Override
    public List<Topic> listOfTopics() throws CrudException {
        List<Topic> registrationList = jdbcTemplate.query(SQL_SELECT_ALL_TOPICS, new Object[] {},
                new TopicRowMapper());
        return registrationList;
    }

    @Override
    public List<Topic> getTopicsPerCourse(List<Long> topicIds) {
        List<Topic> topicList = new ArrayList<>();
        for(Long topicId : topicIds) {
            Map<String, Object> mapFromTopic = new HashMap<>();
            mapFromTopic = jdbcTemplate.queryForMap(SQL_SELECT_ALL_TOPICS_PER_COURSE, topicId);
            Topic topic = new Topic((long) mapFromTopic.get("topic_id"), (String) mapFromTopic.get("topic_name"));
            topicList.add(topic);
        }
        return topicList;
    }

    private class TopicRowMapper implements RowMapper<Topic>
    {
        @Override
        public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
            Topic topic = new Topic(resultSet.getLong("topic_id"), resultSet.getString("topic_name"));
            return topic;
        }
    }
}
