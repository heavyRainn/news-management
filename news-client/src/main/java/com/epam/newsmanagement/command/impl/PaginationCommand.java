package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.CommandException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.impl.AuthorServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PaginationCommand implements Command {

    private static final Logger logger = Logger.getLogger(PaginationCommand.class);

    private static final String ALL_THEMES = "allThemes";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String ALL_NEWS = "allNews";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ITEMS_ON_PAGE_STRING = "itemsOnPage";
    private static final String PAGE_NUMBER = "page";
    private static final int ITEMS_ON_PAGE = 3;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int pageNumber = Integer.valueOf(request.getParameter(PAGE_NUMBER)) - 1;

        logger.info("PAGE_NUMBER : " + pageNumber);

        NewsService newsService = ctx.getBean(NewsService.class);
        CrudService<Author> authorService = ctx.getBean(AuthorServiceImpl.class);

        request.setAttribute(ALL_THEMES, newsService.viewAllThemes());
        request.setAttribute(ALL_AUTHORS, authorService.read());
        request.setAttribute(ALL_NEWS, newsService.viewAllNews(ITEMS_ON_PAGE * pageNumber + 1, ITEMS_ON_PAGE * pageNumber + ITEMS_ON_PAGE));
        request.setAttribute(TOTAL_COUNT, newsService.totalCount());
        request.setAttribute(ITEMS_ON_PAGE_STRING, ITEMS_ON_PAGE);

        return PageName.HOME_PAGE;
    }

}
