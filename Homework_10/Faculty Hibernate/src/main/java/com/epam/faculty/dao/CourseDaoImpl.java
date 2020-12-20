package com.epam.faculty.dao;

import com.epam.faculty.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.save(course);
    }

    @Override
    public Course get(String courseName) {
        Session session = sessionFactory.getCurrentSession();
        Object singleResult = session
                .getNamedQuery("courseByName")
                .setParameter("nameCourse", courseName)
                .getSingleResult();
        return(Course) singleResult;
    }

    @Override
    public Course get(UUID courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        return Objects.requireNonNull(course, "Course not found by course id: " + courseId);
    }
}
