package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.security.HashPassword;
import com.faculty.service.FacultyUserService;
import com.faculty.validation.UserInputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RegistrationPage {

    private static Logger logger = LoggerFactory.getLogger(RegistrationPage.class);
    @Autowired
    private FacultyUserService facultyUserService;
    private UserInputValidator userInputValidator = UserInputValidator.getInstance();

    @RequestMapping(value="/education-faculty.com.ua/registration", method = GET)
    public String getRegistration() {
        return "registration/registrationPage";

    }
    @RequestMapping(value="/education-faculty.com.ua/registration", method = POST)
    public String executeRegistration() throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession(true);
        FacultyUser facultyUserCreate = extractUserFromRequest(request);
        // if fields are not empty we follow the below block
        if (!isUserEmpty(facultyUserCreate)) {
            try {
                // check that such login exists
                if (suchLoginExist(facultyUserCreate, request)) {
                    setRequest(request, facultyUserCreate);
                    session.setAttribute("log", facultyUserCreate.getLogin());
                    return "registration/registrationPage";
                    }
                else {
                    Long userId = null;
                    FacultyUser registeredFacultyUser = null;
                    try {
                        userId = facultyUserService.registrationUser(facultyUserCreate);
                        registeredFacultyUser = facultyUserService.getUserByLogin(facultyUserCreate.getLogin());
                    }
                    // if can't save FacultyUser to database
                    catch (CrudException crudException) {
                        session.setAttribute("crudException", crudException.getMessage());
                        return "redirect:/education-faculty.com.ua/attention-page";
                    } catch (ValidationException validation) {
                        session.setAttribute("validation", validation.getMessage());
                        return "redirect:/education-faculty.com.ua/attention-page";
                    }
                    FacultyUserDto facultyUserDto = UserConverter.userToUserViewDto(registeredFacultyUser);
                    session.setAttribute("user", facultyUserDto);
                    return "redirect:/education-faculty.com.ua/registration/user-profile/id=" + userId;
                }
            } catch (ValidationException validation) {
                session.setAttribute("validation", validation.getMessage());
                return "redirect:/education-faculty.com.ua/attention-page";
            }
        } else {
            setRequest(request, facultyUserCreate);
            return "registration/registrationPage";
        }
    }

    private FacultyUser extractUserFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        String password = HashPassword.hashPassword(request.getParameter("password"));
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        return new FacultyUser(firstName, lastName, login, password, email, telephone);
    }

    private boolean isUserEmpty(FacultyUser facultyUser) throws IOException {
        // if some field is empty we get - true
        if (!userInputValidator.isFirstNameValid(facultyUser.getFirstName()) ||
                !userInputValidator.isLastNameValid(facultyUser.getLastName()) ||
                !userInputValidator.isLoginValid(facultyUser.getLogin()) ||
                !userInputValidator.isEmailValid(facultyUser.getEmail()) ||
                !userInputValidator.isTelephoneValid(facultyUser.getTelephone()) ||
                !userInputValidator.isPasswordValid(facultyUser.getPassword())) {
            return true;
        }
        return false;
    }

    private boolean suchLoginExist(FacultyUser facultyUser, HttpServletRequest request) throws ValidationException {
        FacultyUser facultyUserDB = null;
        facultyUserDB = facultyUserService.getUserByLogin(facultyUser.getLogin());
        Optional<FacultyUser> user = Optional.ofNullable(facultyUserDB);
        if (user.isPresent()) {
            logger.info("Such login exist: {}", facultyUser.getLogin());
            return true;
        }
        return false;
    }

    private void setRequest(HttpServletRequest request, FacultyUser facultyUserCreate) {
        request.setAttribute("first_name", facultyUserCreate.getFirstName());
        request.setAttribute("last_name", facultyUserCreate.getLastName());
        request.setAttribute("login", facultyUserCreate.getLogin());
        request.setAttribute("telephone", facultyUserCreate.getTelephone());
        request.setAttribute("email", facultyUserCreate.getEmail());
    }
}
