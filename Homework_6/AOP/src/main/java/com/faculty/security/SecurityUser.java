package com.faculty.security;

import com.faculty.model.FacultyUser;

/**
 * Created by Aleksandr on 27.09.2020.
 */
public class SecurityUser {

    public static boolean isCorrectPassword(FacultyUser user, String password) {

        return HashPassword.checkPassword(password, user.getPassword());
    }
}
