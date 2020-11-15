package com.faculty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Aleksandr on 14.11.2020.
 */
@Controller
public class UserProfile {

    private static Logger logger = LoggerFactory.getLogger(UserProfile.class);

    @RequestMapping(value = "/education-faculty.com.ua/registration/user-profile/*", method = GET)
    public String getUserProfile() {
        return "user/userProfile";
    }

}
