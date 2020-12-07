package com.faculty.service.Impl;


//import com.faculty.aspectXML.GetExecutionTimeXML;
import com.faculty.aspectXML.ExecutionTimeXML;
import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.Registration;
import com.faculty.repository.registration.RegistrationDao;
import com.faculty.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@ExecutionTimeXML
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationDao registrationDao;

    @Autowired
    public RegistrationServiceImpl(RegistrationDao registrationDao) throws ApplicationException {
        this.registrationDao = registrationDao;
    }

    public RegistrationServiceImpl() throws ApplicationException {
    }

    @Override
    public Long registrationOnCourse(Long courseId, Long userId, Boolean approve) throws CrudException {
        return registrationDao.registrationOnCourse(courseId, userId, approve);
    }

    @Override
    public void approveRegistrationOnCourse(Connection connection, Long courseId, Long userId, Boolean approve) throws CrudException {
        registrationDao.approveRegistrationOnCourse(connection, courseId, userId, approve);
    }

    //@GetExecutionTimeXML
    @Override
    public void deleteRegistration(Long registrationId) throws CrudException {
        registrationDao.deleteRegistration(registrationId);
    }

    //@GetExecutionTimeXML
    @Override
    public List<Registration> getListRegistrationOfUser(Long userId) throws CrudException {
        return registrationDao.getListRegistrationOfUser(userId);
    }

    //@GetExecutionTimeXML
    @Override
    public Registration getRegistrationByCourseUserId(Long courseId, Long userId) throws CrudException {
        return registrationDao.getRegistrationByCourseUserId(courseId, userId);
    }

    //@GetExecutionTimeXML
    @Override
    public List<Registration> getListRegistrationOnCourseId(Long courseId) throws CrudException {
        return registrationDao.getListRegistrationOnCourseId(courseId);
    }

    //@GetExecutionTimeXML
    @Override
    public List<Registration> getListActionApprove(Boolean approve) throws CrudException {
        return registrationDao.getListActionApprove(approve);
    }

    //@GetExecutionTimeXML
    @Override
    public void evaluateUser(Integer userMark, Long userId, Long courseId) throws CrudException {
        registrationDao.evaluateUser(userMark, userId, courseId);
    }
}
