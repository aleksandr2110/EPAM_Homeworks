package com.faculty.repository;

import com.faculty.exception.CrudException;
import com.faculty.model.Topic;

import java.util.List;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public interface TopicDao {
    Long saveTopic(String topicName) throws CrudException;
    void updateTopic(Long topicId, String topicName) throws CrudException;
    void deleteTopic(Long topicId) throws CrudException;
    List<Topic> listOfTopics() throws CrudException;
    List<Topic> getTopicsPerCourse(List<Long> topic);
}
