package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.security.SecurityUser;
import com.faculty.service.FacultyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class Login {

    private static Logger logger = LoggerFactory.getLogger(Login.class);
    @Autowired
    private FacultyUserService facultyUserService;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/education-faculty.com.ua/login")
    public String login() {
         return "user/loginPage";
    }

    @PostMapping("/education-faculty.com.ua/login")
    public String getLogin(@RequestParam(value = "login") String username, @RequestParam(value = "password") String password, Principal principal)
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        FacultyUser facultyUser = null;
        HttpSession session = request.getSession(true);
        try {
            //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            facultyUser = facultyUserService.login(username);
            //facultyUser = facultyUserService.login(auth.getName());
            if (!SecurityUser.isCorrectPassword(facultyUser, password)) {
                logger.error("Wrong password or login! User login: {}", username);
                throw new ValidationException("Wrong password or login!");
            }
            FacultyUserDto userViewDto = UserConverter.userToUserViewDto(facultyUser);
            Long userId = userViewDto.getUserId();
            if (userViewDto != null) {
                if (userViewDto.getRole().equals("admin")) {
                    session.setAttribute("user", userViewDto);
                    return "redirect:/education-faculty.com.ua/admin";
                } else if (userViewDto.getRole().equals("teacher")) {
                    session.setAttribute("user", userViewDto);
                    return "redirect:/education-faculty.com.ua/registration/teacher-page/id=" + userId;
                } else {
                    session.setAttribute("user", userViewDto);
                    return "redirect:/education-faculty.com.ua/registration/user-page/id=" + userId;
                }
            } else {
                String absentData = "неверный логин или пароль";
                session.setAttribute("absentData", absentData);
                return "redirect:/education-faculty.com.ua/registration";
            }
        } catch (ValidationException validation) {
            session.setAttribute("inputValidation", validation.getMessage());
            logger.error("Login method getLogin(): {}, ", validation.getMessage());
            return "redirect:/education-faculty.com.ua/attention-page";
        }
    }
}
