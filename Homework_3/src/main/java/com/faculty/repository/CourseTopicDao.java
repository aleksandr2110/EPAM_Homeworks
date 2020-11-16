package com.faculty.repository;

import com.faculty.exception.CrudException;
import com.faculty.model.CourseTopic;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public interface CourseTopicDao {
    void saveCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException;
    void updateCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException;
    void deleteCourseTopicByCourseId(Connection connection, Long courseId) throws CrudException;
    void deleteTopicFromCourseTopic(Long courseId, Long topicId) throws CrudException;
    List<CourseTopic> getListTopicById(Long courseId) throws CrudException;
    List<CourseTopic> getListCoursesByTopicId(Long topicId) throws CrudException;
    List<CourseTopic> getListTopic() throws CrudException;
}
