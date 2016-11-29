package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.User;
import com.epam.newsmanagement.exception.CommandException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.UserService;
import com.epam.newsmanagement.service.impl.AuthorServiceImpl;
import com.epam.newsmanagement.util.Coder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;

import javax.servlet.http.HttpServletRequest;

@Configurable
public class LoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private static final String LOGIN = "login";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String ALL_THEMES = "allThemes";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String ALL_NEWS = "allNews";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ITEMS_ON_PAGE_STRING = "itemsOnPage";
    private static final int ITEMS_ON_PAGE = 3;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logger.info("LoginCommand.execute( " + request.getParameter(LOGIN) + " , " + Coder.getHashedPassword(request.getParameter(PASSWORD)) + ")");

        UserService userService = ctx.getBean(UserService.class);
        NewsService newsService = ctx.getBean(NewsService.class);
        CrudService<Author> authorService = ctx.getBean(AuthorServiceImpl.class);

        User user = userService.findByLogin(request.getParameter(LOGIN));

        if ((user != null) && (Coder.getHashedPassword(request.getParameter(PASSWORD)).equals(user.getPassword()))) {
            request.getSession().setAttribute(USER, user);
            request.getSession().setAttribute(ROLE, userService.findRoleById(user.getId()));

            request.setAttribute(ALL_THEMES, newsService.viewAllThemes());
            request.setAttribute(ALL_AUTHORS, authorService.read());
            request.setAttribute(ALL_NEWS, newsService.viewAllNews(0, ITEMS_ON_PAGE));
            request.setAttribute(TOTAL_COUNT, newsService.totalCount());
            request.setAttribute(ITEMS_ON_PAGE_STRING, ITEMS_ON_PAGE);

            return PageName.HOME_PAGE;
        } else {
            return PageName.LOGIN_PAGE;
        }

    }
}
