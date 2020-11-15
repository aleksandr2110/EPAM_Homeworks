package com.faculty.filter;

import com.faculty.dto.FacultyUserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Aleksandr on 11.10.2020.
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE
},
        urlPatterns = { "/education-faculty.com.ua/admin/*"
        },   // (+) 1 или более раз
        initParams = {  // параметры инициализации задаются аннотацией
                @WebInitParam(name="encoding", value = "UTF-8", description = "Encoding Param") } // 536
)
public class AdminFilter implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
        if (code == null)
            code = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);
        Optional<HttpSession> optionalSession = Optional.ofNullable(session);
        if(optionalSession.isPresent()) {
            FacultyUserDto userViewDto = (FacultyUserDto) session.getAttribute("user");
            Optional<FacultyUserDto> facultyUserDto = Optional.ofNullable(userViewDto);
            if(facultyUserDto.isPresent()) {
                Boolean userBlocked = userViewDto.isUserBlocked();
                if (userViewDto.getRole().equals("admin") && !userBlocked) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("/jsp/user/userBlocked.jsp");
                    requestDispatcher.forward( httpRequest, httpResponse);
                }
            }
            else {
                httpResponse.sendRedirect("/education-faculty.com.ua/registration");
            }
        }
        else {
            httpResponse.sendRedirect("/education-faculty.com.ua/registration");
        }
    }

    @Override
    public void destroy() {
        code = null;
    }
}
