package com.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
/**
 * Created by Aleksandr on 09.11.2020.
 */
@Controller
public class FacultyStartPage {

    @RequestMapping(value="/education-faculty.com.ua", method = GET)
    public String getStartPage() {
        return "start/startPage";
    }
}
