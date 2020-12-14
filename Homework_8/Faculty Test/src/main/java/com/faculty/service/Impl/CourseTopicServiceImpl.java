package com.faculty.service.Impl;

import com.faculty.aspect.ExecutionTime;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.CourseTopic;
import com.faculty.repository.courseTopic.CourseTopicDao;
import com.faculty.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class CourseTopicServiceImpl implements CourseTopicService {

    private CourseTopicDao courseTopicDao;

    @Autowired
    public CourseTopicServiceImpl(CourseTopicDao courseTopicDao) throws ApplicationException {
        this.courseTopicDao = courseTopicDao;
    }

    public CourseTopicServiceImpl() throws ApplicationException {
    }

    @ExecutionTime
    @Override
    public void saveCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException{
        courseTopicDao.saveCourseTopic(connection, courseId, topicsId);
    }

    @ExecutionTime
    @Override
    public void updateCourseTopic(Connection connection, Long courseId, List<Long> topicsId) throws CrudException {
        courseTopicDao.updateCourseTopic(connection, courseId, topicsId);
    }

    @ExecutionTime
    @Override
    public void deleteCourseTopicByCourseId(Connection connection, Long courseId) throws CrudException {
        courseTopicDao.deleteCourseTopicByCourseId(connection, courseId);
    }

    @ExecutionTime
    @Override
    public void deleteTopicFromCourseTopic(Long courseId, Long topicId) throws CrudException {
        courseTopicDao.deleteTopicFromCourseTopic(courseId, topicId);
    }

    @ExecutionTime
    @Override
    public List<CourseTopic> getListTopicById(Long courseId) throws CrudException {
        return courseTopicDao.getListTopicById(courseId);
    }

    @ExecutionTime
    @Override
    public List<CourseTopic> getListCoursesByTopicId(Long topicId) throws CrudException {
        return courseTopicDao.getListCoursesByTopicId(topicId);
    }

    @ExecutionTime
    @Override
    public List<CourseTopic> getListTopic() throws CrudException {
        return courseTopicDao.getListTopic();
    }
}
