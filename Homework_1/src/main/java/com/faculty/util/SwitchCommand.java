package com.faculty.util;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr on 13.11.2020.
 */
public class SwitchCommand extends Command {

    private static Logger logger = LoggerFactory.getLogger(SwitchCommand.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTopicService topicService;
    @Autowired
    private FacultyUserService facultyUserService;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response, String role)  {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            switch (command) {
                case "courses-asc":
                    String asc = request.getParameter("asc");
                    List<Course> listCoursesAsc = courseService.sortCourses(asc);
                    session.removeAttribute("listCourses");
                    session.setAttribute("listCourses", listCoursesAsc);
                    return "redirect:/education-faculty.com.ua/" + role + "/courses";
                    //response.sendRedirect("/education-faculty.com.ua/" + role + "/courses");
                    //break;
                case "courses-desc":
                    String desc = request.getParameter("desc");
                    List<Course> listCoursesDesc = courseService.sortCourses(desc);
                    session.removeAttribute("listCourses");
                    session.setAttribute("listCourses", listCoursesDesc);
                    return "redirect:/education-faculty.com.ua/" + role + "/courses";
                    //response.sendRedirect("/education-faculty.com.ua/" + role + "/courses");
                    //break;
                case "courses-by-topic":
                    List<Topic> listTopic = (List<Topic>) session.getAttribute("listTopic");
                    Map<Long, String> descriptionTopics = getMapTopics(listTopic);
                    Long topicId = Long.parseLong(request.getParameter("topicId"));
                    // get list CourseTopic by topicId
                    List<CourseTopic> listCourseTopic = topicService.getListCoursesByTopicId(topicId);
                    List<Long> listCourseId = getListCourseId(listCourseTopic);
                    List<Course> listCourses = listCourses(listCourseId);
                    session.setAttribute("listCourses", listCourses);
                    session.setAttribute("descriptionTopics", descriptionTopics);
                    session.setAttribute("topicIdFromReq", topicId);
                    return "redirect:/education-faculty.com.ua/" + role + "/courses";
                    //response.sendRedirect("/education-faculty.com.ua/" + role + "/courses");
                    //break;
                case "courses-by-teacher":
                    Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                    FacultyUser teacher = facultyUserService.getUserById(teacherId);
                    FacultyUserDto teacherDto = UserConverter.userToUserViewDto(teacher);
                    List<Course> listCoursesByTeacher = courseService.extractTeacherCourses(teacherId);
                    session.setAttribute("teacherDto", teacherDto);
                    //session.setAttribute("teacherIdFromReq", teacherId);
                    session.setAttribute("listCourses", listCoursesByTeacher);
                    return "redirect:/education-faculty.com.ua/" + role + "/courses";
                    //response.sendRedirect("/education-faculty.com.ua/" + role + "/courses");
                    //break;
            }
        } catch (CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("SwitchCommand method executeCommand() with role: {}, {}", role, crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
            //response.sendRedirect("/education-faculty.com.ua/attention-page");
        }
        return null;
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
}
