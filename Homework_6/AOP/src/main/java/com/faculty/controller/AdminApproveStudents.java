package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.FacultyUser;
import com.faculty.model.Registration;
import com.faculty.service.CourseService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminApproveStudents {

    private static Logger logger = LoggerFactory.getLogger(AdminApproveStudents.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private FacultyUserService facultyUserService;

    @RequestMapping(value="/education-faculty.com.ua/admin/students", method = GET)
    public String approveStudents() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        try {
            // get all not proved students registration
            List<Registration> listRegistration = registrationService.getListActionApprove(false);
            List<Long> listCourseId = getListCourseId(listRegistration);
            List<Long> listFacultyUserId = getListFacultyUserId(listRegistration);
            Map<Long, Course> coursesMap = getCoursesOnCourseIdList(listCourseId);
            Map<Long, FacultyUserDto> facultyUsersMap = getFacultyUserOnFacultyUserId(listFacultyUserId);
            session.setAttribute("listRegistration", listRegistration);
            session.setAttribute("coursesMap", coursesMap);
            session.setAttribute("facultyUsersMap", facultyUsersMap);
        }
        catch(CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            return "/education-faculty.com.ua/page";
        }
        return "admin/orderForAttendance";
    }

    @RequestMapping(value="/education-faculty.com.ua/admin/students/*", method = POST)
    public String executeApproveStudents()
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        HttpSession session = request.getSession(false);
        try {
            switch(command) {
                // admin approve request on course registration
                case "approve":
                    Long registrCourseId = Long.parseLong(request.getParameter("registrationCourseId"));
                    Long registrUserId = Long.parseLong(request.getParameter("registrationUserId"));
                    approveStudent(registrCourseId, registrUserId);
                    break;
            }
        }
        catch(CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/page";
        }
        return "redirect:/education-faculty.com.ua/admin/students";
    }

    @Transactional
    private synchronized void approveStudent(Long registrCourseId, Long registrUserId) throws CrudException {
        Connection connection = null;
        registrationService.approveRegistrationOnCourse(connection, registrCourseId, registrUserId, true);
        Course course = courseService.extractCourse(connection, registrCourseId);
        Integer newCountStudents = course.getStudentCount() + 1;
        courseService.addStudentByCourseId(connection, registrCourseId, newCountStudents);
    }

    // get map with courseId and value course
    private Map<Long, Course> getCoursesOnCourseIdList(List<Long> listCourseId) throws CrudException {
        Map<Long, Course> mapCourses = new HashMap<>();
        for(Long courseId : listCourseId) {
            Course course = courseService.extractCourse(courseId);
            mapCourses.put(courseId, course);
        }
        return mapCourses;
    }
    // get list of FacultyUser id from registration
    private Map<Long, FacultyUserDto> getFacultyUserOnFacultyUserId(List<Long> listFacultyUserId) throws CrudException {
        Map<Long, FacultyUserDto> mapFacultyUser = new HashMap();
        for(Long userId : listFacultyUserId) {
            FacultyUser facultyUser = facultyUserService.getUserById(userId);
            FacultyUserDto facultyUserDto = UserConverter.userToUserViewDto(facultyUser);
            mapFacultyUser.put(userId, facultyUserDto);
        }
        return mapFacultyUser;
    }
    // get list of Course id from registration list
    private List<Long> getListCourseId(List<Registration> listRegNotApproved) {
        List<Long> listCourseId = new ArrayList<>();
        for(Registration registrationNotApproved : listRegNotApproved) {
            listCourseId.add(registrationNotApproved.getCourseId());
        }
        return listCourseId;
    }
    // get list of FacultyUser id from registration list
    private List<Long> getListFacultyUserId(List<Registration> listRegNotApproved) {
        List<Long> listFacultyUserId = new ArrayList<>();
        for(Registration registrationNotApproved : listRegNotApproved) {
            listFacultyUserId.add(registrationNotApproved.getUserId());
        }
        return listFacultyUserId;
    }
}
