package com.faculty.controller;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class AuthorizationTag extends TagSupport {

    private String role;
    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException
    {
        try {
            String showToClient = null;
            if("user".equals(role))
            {
                showToClient = "Зарегистрирированные пользователи могут делать запрос поступление на курс";
            }
            else
            {
                showToClient = "Не зарегистрированные пользователи не могут делать запрос поступление на курс";
            }
            pageContext.getOut().write("<hr/>" + showToClient + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
