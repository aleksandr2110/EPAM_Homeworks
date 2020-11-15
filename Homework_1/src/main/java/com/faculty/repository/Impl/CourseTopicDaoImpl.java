package com.faculty.repository.Impl;

import com.faculty.exception.CrudException;
import com.faculty.model.CourseTopic;
import com.faculty.repository.CourseTopicDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aleksandr on 10.11.2020.
 */
@Repository
public class CourseTopicDaoImpl implements CourseTopicDao {

    private static Logger logger = LoggerFactory.getLogger(CourseTopicDaoImpl.class);
    @Autowired
    protected DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    public static final String SQL_SAVE_COURSE_TOPICS = "INSERT INTO course_topic (course_id, topic_id) VALUES(?, ?)";
    public static final String SQL_GET_ALL_COURSE_TOPIC = "SELECT * FROM course_topic";
    public static final String SQL_GET_ALL_COURSE_TOPIC_BY_ID = "SELECT * FROM course_topic WHERE course_id = ?";
    public static final String SQL_DELETE_TOPIC_FROM_COURSE_TOPIC = "DELETE FROM course_topic WHERE topic_id=? AND course_id=?";
    public static final String SQL_DELETE_TOPICS_BY_COURSE_ID = "DELETE FROM course_topic WHERE course_id=?"; //
    public static final String SQL_DELETE_COURSE_TOPIC_BY_COURSE_ID = "DELETE FROM course_topic WHERE course_id=?";
    public static final String SQL_SELECT_COURSE_TOPIC_BY_TOPIC_ID = "SELECT * FROM course_topic WHERE topic_id = ?";
    public static final String SQL_DELETE_COURSE_ON_ID = "DELETE FROM course WHERE course_id =?";

    public  CourseTopicDaoImpl() {}

    @Override
    public void saveCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        List<CourseTopic> courseTopicList = new ArrayList<>();
        for (Long topicId : topicsId) {
            courseTopicList.add(new CourseTopic(courseId, topicId));
        }
        int[] updateCounts = jdbcTemplate.batchUpdate(SQL_SAVE_COURSE_TOPICS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, courseTopicList.get(i).getCourseId());
                preparedStatement.setLong(2, courseTopicList.get(i).getTopicId());
            }

            @Override
            public int getBatchSize() {
                return courseTopicList.size();
            }
        });
    }

    // update CourseTopic
    @Override
    public void updateCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        jdbcTemplate.update(SQL_DELETE_TOPICS_BY_COURSE_ID, courseId);
    }

    // delete from CourseTopic on courseId
    @Override
    public void deleteCourseTopicByCourseId(Connection connection, Long courseId) throws CrudException {
        jdbcTemplate.update(SQL_DELETE_TOPICS_BY_COURSE_ID, courseId);
    }

    @Override
    public void deleteTopicFromCourseTopic(Long courseId, Long topicId) throws CrudException {
        jdbcTemplate.update(SQL_DELETE_TOPIC_FROM_COURSE_TOPIC, courseId, topicId);
    }

    @Override
    public List<CourseTopic> getListTopicById(Long courseId) throws CrudException {
        List<CourseTopic> listCourseTopic = jdbcTemplate.query(SQL_GET_ALL_COURSE_TOPIC_BY_ID,
                new Object[] { courseId },
                new RowMapper<CourseTopic>() {
                    @Override
                    public CourseTopic mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new CourseTopic(rs.getLong("course_topic_id"), rs.getLong("course_id"),
                                 rs.getLong("topic_id"));

                    }
                });
        return listCourseTopic;
    }

    @Override
    public List<CourseTopic> getListCoursesByTopicId(Long topicId) throws CrudException {
        List<CourseTopic> listCourseTopic = jdbcTemplate.query(SQL_GET_ALL_COURSE_TOPIC_BY_ID,
                new Object[] { topicId }, new CourseTopicRowMapper());
        return listCourseTopic;

    }

    @Override
    public List<CourseTopic> getListTopic() throws CrudException {
        return jdbcTemplate.query(SQL_GET_ALL_COURSE_TOPIC, new CourseTopicRowMapper());
    }

    private class CourseTopicRowMapper implements RowMapper<CourseTopic> {

        @Override
        public CourseTopic mapRow(ResultSet resultSet, int i) throws SQLException {
            CourseTopic courseTopic = new CourseTopic(resultSet.getLong("course_topic_id"), resultSet.getLong("course_id"),
                    resultSet.getLong("topic_id"));
            return courseTopic;
        }
    }
}
