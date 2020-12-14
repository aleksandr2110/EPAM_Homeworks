package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.FacultyUser;
import com.faculty.model.Topic;
import com.faculty.service.CourseService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.TopicService;
import com.faculty.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
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
    private FacultyUserService facultyUserService;

    private HttpSession session;

    @Autowired
    private Command command;

    public UserCourses() {
    }
    /*
    public UserCourses(TopicService topicService, CourseService courseService, FacultyUserService facultyUserService){
        this.topicService = topicService;
        this.courseService = courseService;
        this.facultyUserService = facultyUserService;
        //this.session = session;
    } */

    @RequestMapping(value = "/education-faculty.com.ua/user/courses", method = GET)
    public String getUserCourse(ModelMap map) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        session = request.getSession(false);
        List<Course> listSessionCourses = new ArrayList();
        List<Topic> listTopic = new ArrayList();
        //listSessionCourses = getListCourses(session);
        listSessionCourses = (List<Course>) session.getAttribute("listCourses");
        Optional<List<Course>> courses = Optional.ofNullable(listSessionCourses);
        try {
            listTopic = topicService.listOfTopics();
            map.addAttribute("listTopic", listTopic);
            //session.setAttribute("listTopic", listTopic);
            if (!courses.isPresent()) {
                List<FacultyUser> teachers = facultyUserService.getUserByRole("teacher");
                List<FacultyUserDto> teachersDto = convertFacultyUser(teachers);
                map.addAttribute("teachersDto", teachersDto);
                //session.setAttribute("teachersDto", teachersDto);
                Map<Long, FacultyUserDto> mapTeachers = getMapTeachers(teachersDto);
                map.addAttribute("mapTeachers", mapTeachers);
                //session.setAttribute("mapTeachers", mapTeachers);
                // get all courses from IT academy
                List<Course> listCourses = courseService.extractCourses();
                map.addAttribute("listCourses", listCourses);
                //session.setAttribute("listCourses", listCourses);
                return "user/userCourses";
            } else {
                return "user/userCourses";
            }
        } catch (CrudException crudException) {
            ModelMap exception = new ExtendedModelMap();
            exception.addAttribute("crudException", crudException.getMessage());
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("{}", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/page";
        }
    }

    @RequestMapping(value = "/education-faculty.com.ua/user/courses/*", method = POST)
    public String executeUserCourses(ModelMap map) throws IOException, ServletException {
        map = new ExtendedModelMap();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession(false);
        FacultyUserDto user = (FacultyUserDto) session.getAttribute("user");
        String role = user.getRole();
        String redirect = command.executeCommand(request, response, role, map);
        return redirect;
    }

    public List<Course> getListCourses(HttpSession session) {
        List<Course> listSessionCourses = new ArrayList();
        listSessionCourses = (List<Course>) session.getAttribute("listCourses");
        return listSessionCourses;
    }

    // convert FacultyUser to FacultyUserDto
    private List<FacultyUserDto> convertFacultyUser(List<FacultyUser> teachers) {
        List<FacultyUserDto> teachersDto = new ArrayList();
        for (FacultyUser teacher : teachers) {
            teachersDto.add(UserConverter.userToUserViewDto(teacher));
        }
        return teachersDto;
    }

    // get map of teacher id and FacultyUserDto
    private Map<Long, FacultyUserDto> getMapTeachers(List<FacultyUserDto> teachersDto) {
        Map<Long, FacultyUserDto> mapTeachers = new HashMap<>();
        for (FacultyUserDto teacher : teachersDto) {
            mapTeachers.put(teacher.getUserId(), teacher);
        }
        return mapTeachers;
    }
}
