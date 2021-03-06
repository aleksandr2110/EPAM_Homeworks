package com.faculty.controller;

import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.Registration;
import com.faculty.service.CourseService;
import com.faculty.service.RegistrationService;
import com.faculty.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserRegCourses {

    private static Logger logger = LoggerFactory.getLogger(UserProgressCourses.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value="/education-faculty.com.ua/user/not-started-courses/*", method = GET)
    public String getUserRegCourses() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        FacultyUserDto userViewDto = (FacultyUserDto) session.getAttribute("user");
        try {
            // get all request for registration on courses
            List<Registration> registrationList = registrationService.getListRegistrationOfUser(userViewDto.getUserId());
            List<Long> listCourseId = getListCourseId(registrationList);
            // get all not started courses of user
            List<Course> listNotStartedCourses = courseService.extractUserCourses(listCourseId, Status.NOTSTARTED.getStatus());
            session.setAttribute("listNotStartedCourses", listNotStartedCourses);
            Map<Long, Registration> registrationMap = getRegistrationOnCourseId(listCourseId, userViewDto.getUserId());
            session.setAttribute("registrationMap", registrationMap);
            return "user/userNotStartedCourses";
        } catch (CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("UserRegCourses method getUserRegCourses(): {}", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }

    // get list of courseId
    private List<Long> getListCourseId(List<Registration> registrationList) {
        List<Long> listCourseId = new ArrayList<>();
        for (Registration registration : registrationList) {
            listCourseId.add(registration.getCourseId());
        }
        return listCourseId;
    }

    // get all registration of user on courseId and userId
    private Map<Long, Registration> getRegistrationOnCourseId(List<Long> listCourseId, Long userId) throws CrudException {
        Map<Long, Registration> registrationMap = new HashMap<>();
        for (Long courseId : listCourseId) {
            registrationMap.put(courseId, registrationService.getRegistrationByCourseUserId(courseId, userId));
        }
        return registrationMap;
    }
}
