package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/comments")
public class NewsmanagementCommentsController {

    @Autowired
    private UserService userService;

    @Autowired
    private CrudService<Comment> commentService;

    private String getLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @ResponseBody
    @PostMapping("/add")
    public Comment add(String text, int newsId) {
        Comment comment = getCompleteComment(newsId, text);

        commentService.create(comment);

        return comment;
    }

    private Comment getCompleteComment(int newsId, String text) {
        Comment comment = new Comment();

        comment.setNewsId(newsId);
        comment.setText(text);
        comment.setUserId(userService.findByLogin(getLogin()).getId());

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        comment.setDate(date);

        return comment;
    }
}
