package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.User;
import com.epam.newsmanagement.exception.CommandException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.UserService;
import com.epam.newsmanagement.util.Coder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private static final String LOGIN = "login";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String ALL_THEMES = "allThemes";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String ALL_NEWS = "allNews";

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CrudService<Author> authorService;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = userService.findByLogin(request.getParameter(LOGIN));

        System.out.println("USERNAME : " + request.getParameter(LOGIN) + " PASSWORD : " + request.getParameter(PASSWORD));
        System.out.println("USER : " + user);

        if (Coder.getHashedPassword(request.getParameter(PASSWORD)).equals(user.getPassword())) {
            request.getSession().setAttribute(USER, user);
            request.getSession().setAttribute(ROLE, userService.findRoleById(user.getId()));

            request.getSession().setAttribute(ALL_THEMES, newsService.viewAllThemes());
            request.getSession().setAttribute(ALL_AUTHORS, authorService.read());
            request.getSession().setAttribute(ALL_NEWS, newsService.viewAllNews(0, 5));
            return PageName.HOME_PAGE;
        } else {
            return PageName.LOGIN_PAGE;
        }

    }
}
