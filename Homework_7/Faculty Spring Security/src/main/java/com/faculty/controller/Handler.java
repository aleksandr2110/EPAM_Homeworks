package com.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class Handler {

    @RequestMapping(value="/education-faculty.com.ua/attention-page", method = GET)
    public String errorPage() {
        return "attention/attentionPage";
    }
}
