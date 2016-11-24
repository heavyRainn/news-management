package com.epam.newsmanagement.command;

import com.epam.newsmanagement.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request) throws CommandException;

}
