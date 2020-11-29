package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.service.FacultyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class AdminPage {

    @Autowired
    private FacultyUserService userService;

    @RequestMapping(value="/education-faculty.com.ua/admin", method = GET)
    public String getAdminPage() {
        return "admin/adminMainPage";
    }

    @RequestMapping(value="/education-faculty.com.ua/admin/*", method = POST)
    public String assignTeacher() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        switch(command) {
            case "search-user":
                String login = request.getParameter("login");
                Optional<String> userLogin = Optional.ofNullable(login);
                // if login inserted
                if(userLogin.isPresent())
                {
                    FacultyUser userFaculty = null;
                    try {
                        userFaculty = userService.getUserByLogin(login);
                    }
                    catch(ValidationException validation) {
                        session.setAttribute("validation", validation.getMessage());
                        return "redirect:/education-faculty.com.ua/attention-page";
                        //response.sendRedirect("/education-faculty.com.ua/attention-page");
                    }
                    // if such login exist
                    Optional<FacultyUser> user = Optional.ofNullable(userFaculty);
                    if(user.isPresent())
                    {
                        FacultyUserDto facultyUserDto = UserConverter.userToUserViewDto(userFaculty);
                        session.setAttribute("facultyUserDto", facultyUserDto);
                        return "admin/adminMainPage";
                    }
                    // if such user doesn't exist
                    else {
                        String userAbsent = "Такого пользователя с логином " + login + " не найдено";
                        request.setAttribute("userAbsent", userAbsent);
                        return "admin/adminMainPage";
                    }
                }
                break;
            case "assign-teacher":
                String loginForTeacher = request.getParameter("teacher");
                // if field isn't empty
                if(!loginForTeacher.isEmpty()) {
                    session.removeAttribute("facultyUserDto");
                    FacultyUser facultyTeacher = null;
                    try {
                        userService.assignTeacher(loginForTeacher);
                        facultyTeacher = userService.getUserByLogin(loginForTeacher);

                    }
                    catch (CrudException e) {
                        session.setAttribute("crudException", e.getMessage());
                        return "redirect:/education-faculty.com.ua/attention-page";
                    }
                    catch (ValidationException e) {
                        session.setAttribute("validation", e.getMessage());
                        return "redirect:/education-faculty.com.ua/attention-page";
                    }
                    FacultyUserDto teacherDto = UserConverter.userToUserViewDto(facultyTeacher);
                    session.setAttribute("teacherDto", teacherDto);
                    return "redirect:/education-faculty.com.ua/admin/courses";
                }
                else {
                    String loginAbsent = "Логин не введен";
                    request.setAttribute("loginAbsent", loginAbsent);
                    return "admin/adminMainPage";
                }
        }
        return "admin/adminMainPage";
    }
}
