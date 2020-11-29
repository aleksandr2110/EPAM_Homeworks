package com.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class Logout {

    @RequestMapping(value="/education-faculty.com.ua/logout", method = GET)
    public String logout() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getRequestURI().equals("/education-faculty.com.ua/logout")) {
            request.getSession(false).invalidate();
        }
        return "redirect:/education-faculty.com.ua";
    }
}
