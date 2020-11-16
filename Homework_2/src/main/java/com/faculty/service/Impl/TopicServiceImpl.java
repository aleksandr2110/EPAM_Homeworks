package com.faculty.service.Impl;

import com.faculty.bpp.Timed;
import com.faculty.exception.CrudException;
import com.faculty.model.Topic;
import com.faculty.repository.TopicDao;
import com.faculty.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Service
@Timed
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao;

    public TopicServiceImpl() {}

    @Autowired
    public TopicServiceImpl(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public Long saveTopic(String topicName)throws CrudException {
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
