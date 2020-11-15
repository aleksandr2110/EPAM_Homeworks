package com.faculty.controller;

import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.exception.CrudException;
import com.faculty.model.FacultyUser;
import com.faculty.service.FacultyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Aleksandr on 13.11.2020.
 */
@Controller
public class AdminBlockStudents {

    @Autowired
    private FacultyUserService facultyUserService;
    private int numberOfRecords;
    private int numberOfPages;
    private int recordsPerPage = 3;
    private int page = 1;

    @RequestMapping(value="/education-faculty.com.ua/admin/students/block-page", method = GET)
    public String adminBlockStudents() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getParameter("currentPage") != null) {
            page = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<FacultyUser> listUsers = facultyUserService.getUserByRole("user", (page - 1) * recordsPerPage, recordsPerPage);
        List<FacultyUserDto> listFacultyUserDto = getListUsersDto(listUsers);
        numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("listFacultyUserDto", listFacultyUserDto);
        return "admin/adminStudentsBlock";
    }


    @RequestMapping(value="/education-faculty.com.ua/admin/students/block-page/*", method = POST)
    public String executeAdminBlockStudents() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            switch (command) {
                // than admin block/unblock user
                case "block":
                    Long userIdBlock = Long.parseLong(request.getParameter("userIdBlock"));
                    facultyUserService.blockUnBlockUser(userIdBlock, true);
                    break;
                case "unblock":
                    Long userIdUnblock = Long.parseLong(request.getParameter("userIdUnblock"));
                    facultyUserService.blockUnBlockUser(userIdUnblock, false);
                    break;
            }
        } catch (CrudException crudException) {
            session.setAttribute("crudException", crudException.getMessage());
            return "redirect:/education-faculty.com.ua/page";
        }
        return "redirect:/education-faculty.com.ua/admin/students/block-page";
    }
    // converts FacultyUser to FacultyUserDto
    private List<FacultyUserDto> getListUsersDto(List<FacultyUser> listUsers) {
        List<FacultyUserDto> listFacultyUsersDto = new ArrayList<>();
        for (FacultyUser facultyUser : listUsers) {
            numberOfRecords++;
            listFacultyUsersDto.add(UserConverter.userToUserViewDto(facultyUser));
        }
        return listFacultyUsersDto;
    }
}
