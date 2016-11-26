package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.exception.CommandException;
import org.springframework.beans.factory.annotation.Configurable;

import javax.servlet.http.HttpServletRequest;

@Configurable
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return PageName.LOGIN_PAGE;
    }

}
