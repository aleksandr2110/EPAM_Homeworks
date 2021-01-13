package com.epam.rd.controller;

import com.epam.rd.entity.FacultyUser;
import com.epam.rd.service.FacultyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FacultyUserController {

    @Autowired
    private FacultyUserService facultyUserService;

    // localhost:8080/usersbyrole?role=user  get list of objects
    @GetMapping(value = "/usersbyrole", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FacultyUser> findUsersByRoleStatusOrder(@RequestParam(value = "role") String role) {
        List<FacultyUser> facultyUsers = facultyUserService.findFacultyUserByRole(role);
        return facultyUsers;
    }

    // localhost:8080/users get all users
    @GetMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FacultyUser> findAllFacultyUsers() {
        List<FacultyUser> facultyUsers = facultyUserService.findAll();
        return facultyUsers;
    }

    // localhost:8080/userid?userid=1 get one object
    @GetMapping(value = "/userid", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FacultyUser findUsersByRoleStatusOrder(@RequestParam(value = "userid") Long userId) {
        FacultyUser facultyUser = facultyUserService.findFacultyUserByUserId(userId);
        return facultyUser;
    }

    @GetMapping(value = "/users-order-by-telephone", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public List<FacultyUser> findOrderByTelephone(@RequestParam(value = "role") String role) {
        List<FacultyUser> orderUsers = facultyUserService.findByRoleOrderByTelephone(role);
        return orderUsers;
    }

    @PostMapping(value = "/user-save", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public FacultyUser saveFacultyUser(@RequestBody FacultyUser facultyUser) {
        return facultyUserService.save(facultyUser);
    }
}
