package com.faculty.controller;

import com.faculty.dto.FacultyUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Aleksandr on 14.11.2020.
 */
@Controller
public class AdminManageTeacher {

    @RequestMapping(value="/education-faculty.com.ua/admin/teachers", method = GET)
    public String getTeachers() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        FacultyUserDto userViewDto = (FacultyUserDto) session.getAttribute("user");
        return "user/userBlocked";
    }
}
