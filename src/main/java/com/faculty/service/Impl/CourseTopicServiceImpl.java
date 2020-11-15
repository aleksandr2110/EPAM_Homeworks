package com.faculty.service.Impl;

import com.faculty.exception.CrudException;
import com.faculty.model.CourseTopic;
import com.faculty.repository.CourseTopicDao;
import com.faculty.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Service
public class CourseTopicServiceImpl implements CourseTopicService {

    private CourseTopicDao courseTopicDao;

    public CourseTopicServiceImpl() {}

    @Autowired
    public CourseTopicServiceImpl(CourseTopicDao courseTopicDao) {
        this.courseTopicDao = courseTopicDao;
    }

    @Override
    public void saveCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        courseTopicDao.saveCourseTopic(connection, courseId, topicsId);
    }

    @Override
    public void updateCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        courseTopicDao.updateCourseTopic(connection, courseId, topicsId);
    }

    @Override
    public void deleteCourseTopicByCourseId(Connection connection, Long courseId) throws CrudException {
        courseTopicDao.deleteCourseTopicByCourseId(connection, courseId);
    }

    @Override
    public void deleteTopicFromCourseTopic(Long courseId, Long topicId) throws CrudException {
        courseTopicDao.deleteTopicFromCourseTopic(courseId, topicId);
    }

    @Override
    public List<CourseTopic> getListTopicById(Long courseId) throws CrudException {
        return courseTopicDao.getListTopicById(courseId);
    }

    @Override
    public List<CourseTopic> getListCoursesByTopicId(Long topicId) throws CrudException {
        return courseTopicDao.getListCoursesByTopicId(topicId);
    }

    @Override
    public List<CourseTopic> getListTopic() throws CrudException {
        return courseTopicDao.getListTopic();
    }
}
