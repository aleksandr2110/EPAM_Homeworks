package com.faculty.controller;

import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.Course;
import com.faculty.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Aleksandr on 14.11.2020.
 */
@Controller
public class TeacherPage {


    private static Logger logger = LoggerFactory.getLogger(TeacherPage.class);
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/education-faculty.com.ua/registration/teacher-page", method = GET)
    public String getTeacherPage() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        try {
            FacultyUserDto teacher = (FacultyUserDto) session.getAttribute("user");
            // get list of courses of teacher
            List<Course> listCourses = courseService.extractTeacherCourses(teacher.getUserId());
            session.setAttribute("listCourses", listCourses);
            return "teacher/teacherPage";
        } catch (CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            logger.error("{}", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }
}
