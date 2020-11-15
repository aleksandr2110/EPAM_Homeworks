package com.faculty.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Aleksandr on 23.10.2020.
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE
},
        urlPatterns = {
                "/education-faculty.com.ua/registration" },   // (+) 1 или более раз
        initParams = {  // параметры инициализации задаются аннотацией
                @WebInitParam(name="encoding", value = "UTF-8", description = "Encoding Param") } // 536
)
public class RegistrationFilter implements Filter {

    String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
        if (code == null)
            code = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if(code!=null && !code.equalsIgnoreCase(codeRequest))
        {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
