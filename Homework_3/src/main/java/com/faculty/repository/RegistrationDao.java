package com.faculty.repository;

import com.faculty.exception.CrudException;
import com.faculty.model.Registration;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public interface RegistrationDao {
    Long registrationOnCourse(Long courseId, Long userId, Boolean approve) throws CrudException;
    void approveRegistrationOnCourse(Connection connection, Long courseId, Long userId, Boolean approve) throws CrudException;
    void deleteRegistration(Long registrationId) throws CrudException;
    List<Registration> getListRegistrationOfUser(Long userId) throws CrudException;
    Registration getRegistrationByCourseUserId(Long courseId, Long userId) throws CrudException;
    List<Registration> getListActionApprove(Boolean approve) throws CrudException;
    List<Registration> getListRegistrationOnCourseId(Long courseId) throws CrudException;
    void evaluateUser(Integer userMark, Long userId, Long courseId) throws CrudException;
}
