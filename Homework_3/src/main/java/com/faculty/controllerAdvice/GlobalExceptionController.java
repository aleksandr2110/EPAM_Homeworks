package com.faculty.controllerAdvice;

import com.faculty.exception.CrudException;
import com.faculty.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Aleksandr on 16.11.2020.
 */
@ControllerAdvice
public class GlobalExceptionController {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(CrudException.class)
    public ModelAndView handleCrudException(CrudException ex, HttpServletRequest request) {
        logger.error("Request: " + request.getRequestURL() + " raised " + ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("crudException", ex.getMessage());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleValidationException(ValidationException ex, HttpServletRequest request) {
        logger.error("Request: " + request.getRequestURL() + " raised " + ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("inputValidation", ex.getMessage());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
