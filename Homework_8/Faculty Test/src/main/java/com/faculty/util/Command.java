package com.faculty.util;

import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Aleksandr on 13.11.2020.
 */
public abstract class Command implements Serializable {

    public abstract String executeCommand(HttpServletRequest request, HttpServletResponse response, String role, ModelMap map)
            throws IOException, ServletException;
}
