package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.exception.CommandException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.impl.AuthorServiceImpl;
import com.epam.newsmanagement.service.impl.NewsServiceImpl;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FilterNewsCommand implements Command {

    private static final Logger logger = Logger.getLogger(FilterNewsCommand.class);

    private static final String ALL_THEMES = "allThemes";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String ALL_NEWS = "allNews";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ITEMS_ON_PAGE_STRING = "itemsOnPage";
    private static final String THEME = "theme";
    private static final String AUTHOR = "author";

    private static final int ITEMS_ON_PAGE = 3;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logger.info("FilterNewsCommand.execute()");

        NewsService newsService = ctx.getBean(NewsServiceImpl.class);
        CrudService<Author> authorService = ctx.getBean(AuthorServiceImpl.class);

        Theme theme = Theme.valueOf(request.getParameter(THEME));
        int authorId = Integer.valueOf(request.getParameter(AUTHOR));

        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria(NewsSearchType.BY_AUTHOR);

        List<Author> authors = new ArrayList<>();

        Author author = new Author(theme.toString(),AUTHOR);
        author.setId(authorId);

        authors.add(author);

        newsSearchCriteria.setAuthors(authors);

        //List<News> news = newsService.viewASingleNews(newsSearchCriteria);
        List<News> news = newsService.viewAllNews(theme, 0, ITEMS_ON_PAGE);

        logger.info("NEEEWS : " + news.toString());

        request.setAttribute(ALL_NEWS, news);
        //request.setAttribute(ALL_NEWS, news);
        request.setAttribute(TOTAL_COUNT, newsService.totalCount(theme));
        request.setAttribute(ITEMS_ON_PAGE_STRING, ITEMS_ON_PAGE);
        request.setAttribute(ALL_THEMES, newsService.viewAllThemes());
        request.setAttribute(ALL_AUTHORS, authorService.read());

        request.getSession().setAttribute(THEME, theme);
        request.getSession().setAttribute(AUTHOR, authorId);

        return PageName.FILTER_HOME;
    }

}
