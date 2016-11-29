package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.CrudCreateException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

@Controller
@RequestMapping("comments")
public class NewsmanagementCommentsController {

    @Autowired
    private UserService userService;

    @Autowired
    private CrudService<Comment> commentService;

    private static final Logger logger = Logger.getLogger(NewsmanagementCommentsController.class);

    private String getLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Comment add(String text, int newsId) {
        logger.info("NewsmanagementCommentsController.add(" + text + ", " + newsId + ")");

        String login = getLogin();

        Comment comment = new Comment();

        comment.setNewsId(newsId);
        comment.setText(text);
        comment.setUserId(userService.findByLogin(login).getId());

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        comment.setDate(date);

        if (commentService.create(comment)) {
            return comment;
        } else {
            throw new CrudCreateException(comment.toString());
        }
    }
}
