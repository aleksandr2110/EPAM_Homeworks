package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.*;
import com.faculty.service.*;
import com.faculty.validation.UserInputValidator;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TeacherCourse {

    @Autowired
    private TopicService topicService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTopicService courseTopicService;
    @Autowired
    private FacultyUserService facultyUserService;
    @Autowired
    private RegistrationService registrationService;
    private UserInputValidator userInputValidator = new UserInputValidator();

    @RequestMapping(value = "/education-faculty.com.ua/teacher-page/course/*", method = GET)
    public String getTeacherCourse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        String courseNumber = uri.substring(uri.lastIndexOf("id=") + 3);
        Long courseId = Long.parseLong(courseNumber);
        try {
            Course course = courseService.extractCourse(courseId);
            List<CourseTopic> listCourseTopics = courseTopicService.getListTopicById(courseId); //
            List<Long> listTopicsId = listTopicId(listCourseTopics);
            // get list of Topic for course
            List<Topic> listTopicsPerCourse = topicService.getTopicsPerCourse(listTopicsId);
            // get list Registration by courseId
            List<Registration> registrationStudents = registrationService.getListRegistrationOnCourseId(courseId);
            List<Long> listUserId = listUserId(registrationStudents);
            List<FacultyUser> listUser = facultyUserService.getUserOnListUserId(listUserId);
            List<FacultyUserDto> listUserDto = convertToUserDto(listUser);
            Map<Long, Integer> mapStudentMark = markOfStudent(registrationStudents);
            session.setAttribute("mapStudentMark", mapStudentMark);
            session.setAttribute("listUserDto", listUserDto);
            session.setAttribute("listTopicsPerCourse", listTopicsPerCourse);
            session.setAttribute("course", course);
            return "teacher/teacherCourse";

        } catch (CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }

    @RequestMapping(value = "/education-faculty.com.ua/teacher-page/course/*", method = POST)
    public String executeTeacher() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            switch (command) {
                // evaluate student
                case "evaluate":
                    String evaluate = request.getParameter("evaluate");
                    if (userInputValidator.isStudentMarkValid(evaluate)) {
                        Integer mark = Integer.parseInt(evaluate);
                        Long userId = Long.parseLong(request.getParameter("userId"));
                        Long courseId = Long.parseLong(request.getParameter("courseId"));
                        registrationService.evaluateUser(mark, userId, courseId);
                        return "/education-faculty.com.ua/teacher-page/course/id=" + courseId;
                    } else {
                        return "/education-faculty.com.ua/teacher-page/courses";
                    }
            }
        } catch (CrudException e) {
            session.setAttribute("crudException", e.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
            //response.sendRedirect("/education-faculty.com.ua/attention-page");
        }
        return "teacher/teacherCourse";
    }

    // get list topicId from list CourseTopic
    private List<Long> listTopicId(List<CourseTopic> listCourseTopics) {
        List<Long> listTopicId = new ArrayList<>();
        for (CourseTopic courseTopic : listCourseTopics) {
            listTopicId.add(courseTopic.getTopicId());
        }
        return listTopicId;
    }

    // get list of userId
    private List<Long> listUserId(List<Registration> listRegistration) {
        List<Long> listUserId = new ArrayList<>();
        for (Registration registration : listRegistration) {
            listUserId.add(registration.getUserId());
        }
        return listUserId;
    }

    // convert to FacultyUserDto
    private List<FacultyUserDto> convertToUserDto(List<FacultyUser> listUser) {
        List<FacultyUserDto> listUserDto = new ArrayList();
        for (FacultyUser user : listUser) {
            listUserDto.add(UserConverter.userToUserViewDto(user));
        }
        return listUserDto;
    }

    // get marks of students
    private Map<Long, Integer> markOfStudent(List<Registration> listRegistration) {
        Map<Long, Integer> mapUserIdMark = new HashMap<>();
        for (Registration registration : listRegistration) {
            Long userId = registration.getUserId();
            Integer mark = registration.getStudentMark();
            mapUserIdMark.put(userId, mark);
        }
        return mapUserIdMark;
    }

}
