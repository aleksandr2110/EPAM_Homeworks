package com.faculty.service.Impl;

import com.faculty.bpp.Timed;
import com.faculty.exception.CrudException;
import com.faculty.model.Registration;
import com.faculty.repository.RegistrationDao;
import com.faculty.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Service
@Timed
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationDao registrationDao;

    public RegistrationServiceImpl() {}

    @Autowired
    public RegistrationServiceImpl(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Override
    public Long registrationOnCourse(Long courseId, Long userId, Boolean approve) throws CrudException {
        return registrationDao.registrationOnCourse(courseId, userId, approve);
    }

    @Override
    public void approveRegistrationOnCourse(Connection connection, Long courseId, Long userId, Boolean approve) throws CrudException  {
        registrationDao.approveRegistrationOnCourse(connection, courseId, userId, approve);
    }

    @Override
    public void deleteRegistration(Long registrationId) throws CrudException  {
        registrationDao.deleteRegistration(registrationId);
    }

    @Override
    public List<Registration> getListRegistrationOfUser(Long userId) throws CrudException  {
        return registrationDao.getListRegistrationOfUser(userId);
    }


    @Override
    public Registration getRegistrationByCourseUserId(Long courseId, Long userId) throws CrudException  {
        return registrationDao.getRegistrationByCourseUserId(courseId, userId);
    }

    @Override
    public List<Registration> getListRegistrationOnCourseId(Long courseId) throws CrudException  {
        return registrationDao.getListRegistrationOnCourseId(courseId);
    }

    @Override
    public List<Registration> getListActionApprove(Boolean approve) throws CrudException  {
        return registrationDao.getListActionApprove(approve);
    }

    @Override
    public void evaluateUser(Integer userMark, Long userId, Long courseId) throws CrudException  {
        registrationDao.evaluateUser(userMark, userId, courseId);
    }
}
