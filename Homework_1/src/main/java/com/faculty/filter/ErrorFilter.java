package com.faculty.filter;

import com.faculty.exception.ApplicationException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aleksandr on 15.10.2020.
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE
},
        urlPatterns = { "/education-faculty.com.ua/attention-page"
                },
        initParams = {
                @WebInitParam(name="encoding", value = "UTF-8", description = "Encoding Param") } // 536
)
public class ErrorFilter implements Filter {
    String code;

    public ErrorFilter() throws ApplicationException {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        code = filterConfig.getInitParameter("encoding");
        if (code == null)
            code = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding(); // возвращает кодировку запроса
        // установка кодировки из параметров фильтра, если не установлена
        if(code!=null && !code.equalsIgnoreCase(codeRequest))
        {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
