package com.epam.faculty.dao;

import com.epam.faculty.entity.FacultyUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Repository
public class FacultyUserDaoImpl implements FacultyUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(FacultyUser facultyUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(facultyUser);
    }

    @Override
    public FacultyUser get(String login) {
        Session session = sessionFactory.getCurrentSession();
        Object singleResult = session
                .getNamedQuery("facultyUserLogin")
                .setParameter("loginParam", login)
                .getSingleResult();
        return(FacultyUser) singleResult;
    }

    @Override
    public FacultyUser get(UUID facultyUsrId) {
        Session session = sessionFactory.getCurrentSession();
        FacultyUser facultyUser = session.get( FacultyUser.class,facultyUsrId);
        return Objects.requireNonNull(facultyUser, "FacultyUser not found on facultyUsrId " + facultyUsrId);
    }


}
