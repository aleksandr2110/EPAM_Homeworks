package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.CourseTopic;
import com.faculty.model.FacultyUser;
import com.faculty.model.Topic;
import com.faculty.service.CourseService;
import com.faculty.service.CourseTopicService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.TopicService;
import com.faculty.util.Command;
import com.faculty.util.Status;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Aleksandr on 14.11.2020.
 */
@Controller
public class AdminCourses {

    private static Logger logger = LoggerFactory.getLogger(AdminCourses.class);
    @Autowired
    private TopicService topicService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTopicService courseTopicService;
    @Autowired
    private FacultyUserService facultyUserService;

    private Command command = new SwitchCommand();

    @RequestMapping(value="/education-faculty.com.ua/admin/courses", method = GET)
    public String getAdminCourses() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        try {
            // get all topics
            List<Topic> listTopic  = topicService.listOfTopics();
            session.setAttribute("listTopic", listTopic);
            List<FacultyUser> teachers = facultyUserService.getUserByRole("teacher");
            List<FacultyUserDto> teachersDto = convertFacultyUser(teachers);
            session.setAttribute("teachersDto", teachersDto);
            List<Course> listSessionCourses = (List<Course>) session.getAttribute("listCourses");
            Optional<List<Course>> courses = Optional.ofNullable(listSessionCourses);
            // if absent courses we get below code
            if(!courses.isPresent()) {
                Map<Long, FacultyUserDto> mapTeachers = getMapTeachers(teachersDto);
                session.setAttribute("mapTeachers", mapTeachers);
                // if not exist get all courses
                List<Course> listCourses = getCourses();
                session.setAttribute("listCourses", listCourses);
                return "admin/adminCourses";
            }
            else {
                return "admin/adminCourses";
            }
        }
        catch(CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("AdminCourses method getAdminCourses() {}", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }

    @RequestMapping(value="/education-faculty.com.ua/admin/courses/*", method = POST)
    public String updateCourse() throws IOException, ServletException  {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession(false);
        FacultyUserDto user = (FacultyUserDto)session.getAttribute("user");
        String role = user.getRole();
        String uri = request.getRequestURI();
        String createCommand = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            // if command is create-course we get bellow code
            if(createCommand.equals("create-course")) {
                Course course = extractCourse(request);
                Long courseId = courseService.saveCourse(course);
                return  "redirect:/education-faculty.com.ua/admin/course/id=" + courseId;
            }
            else {
                String redirect = command.executeCommand(request, response, role);
                return redirect;
                /*
                String command = uri.substring(uri.lastIndexOf("/") + 1);
                try {

                    switch (command) {
                        case "courses-asc":
                            String asc = request.getParameter("asc");
                            List<Course> listCoursesAsc = courseService.sortCourses(asc);
                            session.removeAttribute("listCourses");
                            session.setAttribute("listCourses", listCoursesAsc);
                            return "redirect:/education-faculty.com.ua/" + role + "/courses";
                        case "courses-desc":
                            String desc = request.getParameter("desc");
                            List<Course> listCoursesDesc = courseService.sortCourses(desc);
                            session.removeAttribute("listCourses");
                            session.setAttribute("listCourses", listCoursesDesc);
                            return "redirect:/education-faculty.com.ua/" + role + "/courses";
                        case "courses-by-topic":
                            List<Topic> listTopic = (List<Topic>) session.getAttribute("listTopic");
                            Map<Long, String> descriptionTopics = getMapTopics(listTopic);
                            Long topicId = Long.parseLong(request.getParameter("topicId"));
                            // get list CourseTopic by topicId
                            List<CourseTopic> listCourseTopic = courseTopicService.getListCoursesByTopicId(topicId);
                            List<Long> listCourseId = getListCourseId(listCourseTopic);
                            List<Course> listCourses = listCourses(listCourseId);
                            session.setAttribute("listCourses", listCourses);
                            session.setAttribute("descriptionTopics", descriptionTopics);
                            session.setAttribute("topicIdFromReq", topicId);
                            return "redirect:/education-faculty.com.ua/" + role + "/courses";
                        case "courses-by-teacher":
                            Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                            FacultyUser teacher = facultyUserService.getUserById(teacherId);
                            FacultyUserDto teacherDto = UserConverter.userToUserViewDto(teacher);
                            List<Course> listCoursesByTeacher = courseService.extractTeacherCourses(teacherId);
                            session.setAttribute("teacherDto", teacherDto);
                            //session.setAttribute("teacherIdFromReq", teacherId);
                            session.setAttribute("listCourses", listCoursesByTeacher);
                            return "redirect:/education-faculty.com.ua/" + role + "/courses";
                    }
                } catch (CrudException crudException) {
                    session.setAttribute("crudException", crudException.getMessage());
                    return "redirect:/education-faculty.com.ua/attention-page";
                }
                */
            }
        }
        catch(CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }

    // get map key = topicId  value = topicName
    private Map<Long, String> getMapTopics(List<Topic> listTopic) {
        Map<Long, String> mapTopics = new HashMap<>();
        for (Topic topic : listTopic) {
            mapTopics.put(topic.getTopicId(), topic.getTopicName());
        }
        return mapTopics;
    }

    // get list courseId by list CourseTopic
    private List<Long> getListCourseId(List<CourseTopic> listCourseTopic) {
        List<Long> listCourseId = new ArrayList<>();
        listCourseId = listCourseTopic.stream()
                .map(CourseTopic::getCourseId)
                .collect(Collectors.toList());
        return listCourseId;
    }

    private List<Course> listCourses(List<Long> listCourseId) throws CrudException {
        List<Course> listCourses = new ArrayList<>();
        for (Long courseId : listCourseId) {
            Course course = courseService.extractCourse(courseId);
            listCourses.add(course);
        }
        return listCourses;
    }

    private Course extractCourse(HttpServletRequest request) {
        String courseName = request.getParameter("courseName");
        String dateTime = request.getParameter("datetime");
        StringBuilder stringBuilder = new StringBuilder(dateTime);
        stringBuilder.replace(10, 11, "T");
        LocalDateTime parsedLocalDateTime = LocalDateTime.parse(stringBuilder.toString());
        Integer duration = Integer.valueOf(request.getParameter("duration"));
        Integer price = Integer.valueOf(request.getParameter("price"));
        Long teacherId = Long.valueOf(request.getParameter("teacher"));
        Course course = new Course(courseName, parsedLocalDateTime, duration, 0, teacherId, Status.NOTSTARTED.getStatus(), price);
        return course;
    }

    private List<Course> getCourses() throws CrudException
    {
        return courseService.extractCourses();
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
