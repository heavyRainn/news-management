package com.epam.newsmanagement.command.impl;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.PageName;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.User;
import com.epam.newsmanagement.exception.CommandException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.impl.CommentServiceImpl;
import com.epam.newsmanagement.service.impl.NewsServiceImpl;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class AddCommentCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddCommentCommand.class);

    private static final String COMMENT_TEXT = "commentText";
    private static final String USER = "user";
    private static final String NEWS_ID = "newsId";
    private static final String CONCRETE_NEWS = "concreteNews";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        logger.info("AddCommentCommand.execute()");

        String text = request.getParameter(COMMENT_TEXT);

        CrudService<Comment> commentService = ctx.getBean(CommentServiceImpl.class);

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        NewsService newsService = ctx.getBean(NewsServiceImpl.class);

        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        newsSearchCriteria.setId(Integer.valueOf(request.getParameter(NEWS_ID)));

        News news = newsService.viewASingleNews(newsSearchCriteria).get(0);

        User user = (User) request.getSession().getAttribute(USER);

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUserId(user.getId());
        comment.setNewsId(Integer.valueOf(request.getParameter(NEWS_ID)));
        comment.setDate(date);

        request.setAttribute(CONCRETE_NEWS, news);

        commentService.create(comment);

        return PageName.CONCRETE_NEWS_PAGE;
    }

}
