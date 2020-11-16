package com.faculty.controller;

import com.faculty.exception.ApplicationException;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.Registration;
import com.faculty.service.*;
import com.faculty.service.Impl.CourseServiceImpl;
import com.faculty.service.Impl.RegistrationServiceImpl;
import com.faculty.util.Status;
import com.faculty.dto.FacultyUserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Aleksandr on 15.11.2020.
 */
@Controller
public class UserFinishedCourses {

    private static Logger logger = LoggerFactory.getLogger(UserFinishedCourses.class);
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value="/education-faculty.com.ua/user/finished-courses/*", method = GET)
    public String userFinishedCourses() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        try {
            FacultyUserDto userViewDto = (FacultyUserDto)session.getAttribute("user");
            // get all request for registration on courses
            List<Registration> registrationList = registrationService.getListRegistrationOfUser(userViewDto.getUserId());
            List<Long> listCourseId = getListCourseId(registrationList);
            // get all started courses of user
            List<Course> listFinishedCourses = courseService.extractUserCourses(listCourseId, Status.FINISHED.getStatus());
            session.setAttribute("listFinishedCourses", listFinishedCourses);
            Map<Long, Registration> registrationMap = getRegistrationOnCourseId(listCourseId, userViewDto.getUserId());
            session.setAttribute("registrationMap", registrationMap);
            return "user/userFinishedCourses";
        }
        catch(CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("UserFinishedCourses method userFinishedCourses {}", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }

    // get list of courseId
    private List<Long> getListCourseId(List<Registration> registrationList) {
        List<Long> listCourseId = new ArrayList<>();
        for(Registration registration : registrationList)
        {
            listCourseId.add(registration.getCourseId());
        }
        return listCourseId;
    }

    // get all registration of user on courseId and userId
    private Map<Long, Registration> getRegistrationOnCourseId(List<Long> listCourseId, Long userId) throws CrudException
    {
        Map<Long, Registration> registrationMap = new HashMap<>();
        for(Long courseId : listCourseId) {
            registrationMap.put(courseId, registrationService.getRegistrationByCourseUserId(courseId, userId));
        }
        return registrationMap;
    }
}
