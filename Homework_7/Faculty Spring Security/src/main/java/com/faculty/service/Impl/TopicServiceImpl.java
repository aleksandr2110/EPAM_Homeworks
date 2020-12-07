package com.faculty.service.Impl;

import com.faculty.aspectXML.ExecutionTimeXML;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.Topic;
import com.faculty.repository.topic.TopicDao;
import com.faculty.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@ExecutionTimeXML
@Service
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao;

    @Autowired
    public TopicServiceImpl(TopicDao topicDao) throws ApplicationException {
        this.topicDao = topicDao;
    }

    public TopicServiceImpl() throws ApplicationException {
    }

    @Override
    public Long saveTopic(String topicName) throws CrudException {
        return topicDao.saveTopic(topicName);
    }

    @Override
    public void updateTopic(Long topicId, String topicName) throws CrudException {
        topicDao.updateTopic(topicId, topicName);
    }

    @Override
    public void deleteTopic(Long topicId) throws CrudException {
        topicDao.deleteTopic(topicId);
    }

    @Override
    public List<Topic> listOfTopics() throws CrudException {
        return topicDao.listOfTopics();
    }

    @Override
    public List<Topic> getTopicsPerCourse(List<Long> topicsId) throws CrudException {
        return topicDao.getTopicsPerCourse(topicsId);
    }
}
