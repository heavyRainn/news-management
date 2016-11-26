package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.exception.CommandException;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocalCommand implements Command {

    private final String URL = "url";
    private final String LOCALE = "language";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;

        String language = request.getParameter(LOCALE);
        request.getSession().setAttribute(LOCALE, language);

        page = (String) request.getSession().getAttribute(URL);

        return page;
    }
}
