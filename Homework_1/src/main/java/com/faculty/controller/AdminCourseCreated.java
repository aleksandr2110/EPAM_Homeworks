package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.model.CourseTopic;
import com.faculty.model.Topic;
import com.faculty.service.CourseService;
import com.faculty.service.CourseTopicService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.TopicService;
import com.faculty.util.Status;
import com.faculty.validation.UserInputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Aleksandr on 14.11.2020.
 */
@Controller
public class AdminCourseCreated {

    private static Logger logger = LoggerFactory.getLogger(AdminCourseCreated.class);
    @Autowired
    private TopicService topicService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTopicService courseTopicService;
    @Autowired
    private FacultyUserService facultyUserService;
    private UserInputValidator userInputValidator = UserInputValidator.getInstance();

    @RequestMapping(value="/education-faculty.com.ua/admin/course", method = GET)
    public String getCourse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        String number = uri.substring(uri.lastIndexOf("id=") + 3);
        Long courseId = Long.parseLong(number);
        try {
            List<Topic> listOfTopics = topicService.listOfTopics();
            Course course = courseService.extractCourse(courseId);
            // get list CourseTopic objects by courseId
            List<CourseTopic> listCourseTopics = courseTopicService.getListTopicById(courseId);
            List<Long> listTopicsId = getListTopicId(listCourseTopics);
            // get list of topics by list topicId
            List<Topic> listTopicsPerCourse = topicService.getTopicsPerCourse(listTopicsId);
            FacultyUserDto teacher = UserConverter.userToUserViewDto(facultyUserService.getUserById(course.getUserId()));
            List<String> statusList = Arrays.asList(Status.NOTSTARTED.getStatus(), Status.INPROGRESS.getStatus(), Status.FINISHED.getStatus());
            session.setAttribute("listOfTopics", listOfTopics);
            session.setAttribute("statusList", statusList);
            session.setAttribute("course", course);
            session.setAttribute("listTopicsPerCourse", listTopicsPerCourse);
            session.setAttribute("teacher", teacher);
            return "admin/adminCourseCreated";
        }
        catch(CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/page";
        }
    }

    @RequestMapping(value="/education-faculty.com.ua/admin/course/*", method = POST)
    public String updateCourse() throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        Course course = (Course) session.getAttribute("course");
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        Connection connection = null;
        try {
            switch (command) {
                // add new topics
                case "add-topics":
                    List<Long> listTopicsIdForSave = extractTopicsId(request);
                    courseTopicService.saveCourseTopic(connection, course.getCourseId(), listTopicsIdForSave);
                    return "/education-faculty.com.ua/admin/course/id=" + course.getCourseId();
                // update existed course
                case "update-course":
                    Course courseUpdate = extractCourseForUpdate(request);
                    // if course is valid we follow the below block
                    if (!isCourseEmpty(courseUpdate)) {
                        courseService.updateCourse(courseUpdate.getCourseId(), courseUpdate.getCourseName(), courseUpdate.getDateTime(),
                                courseUpdate.getDurationWeeks(), courseUpdate.getUserId(), courseUpdate.getStatus(), courseUpdate.getPrice());
                        return "redirect:/education-faculty.com.ua/admin/course/id=" + courseUpdate.getCourseId();
                    } else {
                        setRequest(request, courseUpdate);
                        return "admin/adminCourseCreated";
                    }
                // delete course
                case "delete-course":
                    Long courseIdForDel = Long.parseLong(request.getParameter("courseIdForDel"));
                    try {
                        if (courseIdForDel == null)
                        {
                            throw new SQLException();
                        }
                    }
                    catch (SQLException e) {
                        throw new CrudException("");
                    }
                    courseTopicService.deleteCourseTopicByCourseId(connection, courseIdForDel);
                    courseService.deleteCourse(connection, courseIdForDel);
                    return "redirect:/education-faculty.com.ua/admin/courses";
                // delete topic
                case "delete-topic":
                    Long topicIdForDel = Long.parseLong(request.getParameter("topicIdInTopicDel"));
                    //Long courseIdDel = Long.parseLong(request.getParameter("courseIdInCourseDel"));
                    courseTopicService.deleteTopicFromCourseTopic(course.getCourseId(), topicIdForDel);
                    return "redirect:/education-faculty.com.ua/admin/course/id=" + course.getCourseId();
                // update topics
                case "update-topic":
                    List<Long> listTopicsId = new ArrayList();
                    Map m = request.getParameterMap();
                    Set s = m.entrySet();
                    Iterator it = s.iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();
                        String[] value = entry.getValue();
                        for (String val : value) {
                            Long topicId = Long.parseLong(val);
                            listTopicsId.add(topicId);
                        }
                    }
                    Long courseId = listTopicsId.get(listTopicsId.size() - 1);
                    listTopicsId.remove(listTopicsId.size() - 1);
                    courseTopicService.updateCourseTopic(connection, courseId, listTopicsId);
                    courseTopicService.saveCourseTopic(connection, courseId, listTopicsId);
                    return "redirect:/education-faculty.com.ua/admin/course/id=" + courseId;
            }
        }

        catch (CrudException e) {
            String sqlException = "Can't get connection to Server, please try latter";
            session.setAttribute("crudException", sqlException);
            return "redirect:/education-faculty.com.ua/attention-page";
        }
        return null;
    }

    private List<Long> extractTopicsId(HttpServletRequest request) {
        List<Long> topicsIdId = new ArrayList();
        List<Long> topicsIdForAdd = new ArrayList();
        topicsIdId.add(Long.parseLong(request.getParameter("topic_1")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_2")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_3")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_4")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_5")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_6")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_7")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_8")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_9")));
        topicsIdId.add(Long.parseLong(request.getParameter("topic_10")));
        for(Long topic : topicsIdId) {
            if(topic > 1)
            {
                topicsIdForAdd.add(topic);
            }
        }
        return topicsIdForAdd;
    }

    // get list of topicId by CourseTopic list
    private List<Long> getListTopicId(List<CourseTopic> listCourseTopics) {
        List<Long> listTopicId = new ArrayList<>();
        for(CourseTopic courseTopic : listCourseTopics)
        {
            listTopicId.add(courseTopic.getTopicId());
        }
        return listTopicId;
    }

    // extract new data from form
    private Course extractCourseForUpdate(HttpServletRequest request) {
        String courseNameUpdate = request.getParameter("courseName");
        String dateTimeUpdate = request.getParameter("dateTime");
        StringBuilder stringBuilder = new StringBuilder(dateTimeUpdate);
        stringBuilder.replace(10, 11, "T");
        LocalDateTime parsedLocalDateTime = LocalDateTime.parse(stringBuilder.toString());
        Integer durationUpdate = Integer.valueOf(request.getParameter("duration"));
        Integer priceUpdate = Integer.valueOf(request.getParameter("price"));
        Long teacherIdUpdate = Long.valueOf(request.getParameter("teacher"));
        String status = request.getParameter("status");
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        Course courseUpdate = new Course(courseId, courseNameUpdate, parsedLocalDateTime, durationUpdate, teacherIdUpdate, status, priceUpdate);
        return courseUpdate;
    }

    private boolean isCourseEmpty(Course courseUpdate) throws IOException {
        if(!userInputValidator.isCourseNameValid(courseUpdate.getCourseName()) ||
                !userInputValidator.isDurationValid(String.valueOf(courseUpdate.getDurationWeeks())) ||
                !userInputValidator.isPriceValid(String.valueOf(courseUpdate.getPrice())))
        {
            return true;
        }
        return false;
    }

    private void setRequest(HttpServletRequest request,  Course courseUpdate) {
        request.setAttribute("courseName", courseUpdate.getCourseName());
        request.setAttribute("duration", courseUpdate.getDurationWeeks());
        request.setAttribute("price", courseUpdate.getPrice());
    }
}
