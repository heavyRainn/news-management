package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.exception.CommandException;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.impl.NewsServiceImpl;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ConcreteNewsCommand implements Command {

    private static final Logger logger = Logger.getLogger(ConcreteNewsCommand.class);

    private static final String NEWS_ID = "newsId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int newsId = Integer.valueOf(request.getParameter(NEWS_ID));

        NewsService newsService = ctx.getBean(NewsServiceImpl.class);

        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        newsSearchCriteria.setId(newsId);

        News news = newsService.viewASingleNews(newsSearchCriteria).get(0);

        request.setAttribute("concreteNews", news);

        logger.info("NEWS ID : " + newsId);

        logger.info("NEWS : " + news);

        return PageName.CONCRETE_NEWS_PAGE;
    }
}
