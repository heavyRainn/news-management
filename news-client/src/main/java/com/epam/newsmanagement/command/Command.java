package com.epam.newsmanagement.command;

import com.epam.newsmanagement.exception.CommandException;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    ApplicationContext ctx = CommandContext.getInstance();

    String execute(HttpServletRequest request) throws CommandException;

}
