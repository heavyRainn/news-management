package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.exception.CommandException;
import org.springframework.beans.factory.annotation.Configurable;

import javax.servlet.http.HttpServletRequest;

@Configurable
public class UnknownCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PageName.HOME_PAGE;
    }

}
