package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.FacultyUser;
import com.faculty.model.Topic;
import com.faculty.service.CourseService;
import com.faculty.service.CourseTopicService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.TopicService;
import com.faculty.util.Command;
import com.faculty.util.SwitchCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserCourses {

    private static Logger logger = LoggerFactory.getLogger(UserCourses.class);
    @Autowired
    private TopicService topicService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTopicService courseTopicService;
    @Autowired
    private FacultyUserService facultyUserService;
    private Command command = new SwitchCommand();

    @RequestMapping(value="/education-faculty.com.ua/user/courses", method = GET)
    public String getUserCourse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HttpSession session = request.getSession(false);
        List<Course> listSessionCourses = new ArrayList();
        List<Topic> listTopic = new ArrayList();
        listSessionCourses = (List<Course>) session.getAttribute("listCourses");
        Optional<List<Course>> courses = Optional.ofNullable(listSessionCourses);
        try {
            listTopic = topicService.listOfTopics();
            session.setAttribute("listTopic", listTopic);
            if (!courses.isPresent()) {
                List<FacultyUser> teachers = facultyUserService.getUserByRole("teacher");
                List<FacultyUserDto> teachersDto = convertFacultyUser(teachers);
                session.setAttribute("teachersDto", teachersDto);
                Map<Long, FacultyUserDto> mapTeachers = getMapTeachers(teachersDto);
                session.setAttribute("mapTeachers", mapTeachers);
                // get all courses from IT academy
                List<Course> listCourses = courseService.extractCourses();
                session.setAttribute("listCourses", listCourses);
                return "user/userCourses";
            } else {
                return "user/userCourses";
            }
        } catch (CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("{}", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/page";
        }
    }

    @RequestMapping(value="/education-faculty.com.ua/user/courses", method = POST)
    public String executeUserCourses() throws IOException, ServletException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession(false);
        FacultyUserDto user = (FacultyUserDto)session.getAttribute("user");
        String role = user.getRole();
        String redirect = command.executeCommand(request, response, role);
        return redirect;
    }
    // convert FacultyUser to FacultyUserDto
    private List<FacultyUserDto> convertFacultyUser(List<FacultyUser> teachers) {
        List<FacultyUserDto> teachersDto = new ArrayList();
        for(FacultyUser teacher : teachers) {
            teachersDto.add(UserConverter.userToUserViewDto(teacher));
        }
        return teachersDto;
    }

    // get map of teacher id and FacultyUserDto
    private Map<Long, FacultyUserDto> getMapTeachers(List<FacultyUserDto> teachersDto) {
        Map<Long, FacultyUserDto> mapTeachers = new HashMap<>();
        for(FacultyUserDto teacher : teachersDto) {
            mapTeachers.put(teacher.getUserId(), teacher);
        }
        return mapTeachers;
    }
}
