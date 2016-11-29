package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocalCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocalCommand.class);

    private final String URL = "url";
    private final String LOCALE = "language";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logger.info("ChangeLocalCommand.execute()");

        String page;

        String language = request.getParameter(LOCALE);
        request.getSession().setAttribute(LOCALE, language);

        page = (String) request.getSession().getAttribute(URL);

        return page;
    }
}
