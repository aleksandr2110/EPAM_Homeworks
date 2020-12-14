package com.faculty.service;

import com.faculty.exception.CrudException;
import com.faculty.model.Topic;

import java.util.List;

/**
 * Created by Aleksandr on 28.09.2020.
 */
public interface TopicService {

    Long saveTopic(String topicName) throws CrudException;

    void updateTopic(Long topicId, String topicName) throws CrudException;

    void deleteTopic(Long topicId) throws CrudException;

    List<Topic> listOfTopics() throws CrudException;

    List<Topic> getTopicsPerCourse(List<Long> topicsId) throws CrudException;
}
