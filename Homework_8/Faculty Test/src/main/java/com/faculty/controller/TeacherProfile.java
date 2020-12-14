package com.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TeacherProfile {

    @RequestMapping(value = "/education-faculty.com.ua/registration/teacher-profile", method = GET)
   public String getTeacherProfile() {
       return "teacher/teacherProfile";
   }
}
