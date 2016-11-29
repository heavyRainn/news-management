package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.exception.CommandException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;

import javax.servlet.http.HttpServletRequest;

@Configurable
public class UnknownCommand implements Command {

    private static final Logger logger = Logger.getLogger(UnknownCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logger.info("UnknownCommand.execute()");

        return PageName.HOME_PAGE;
    }

}
