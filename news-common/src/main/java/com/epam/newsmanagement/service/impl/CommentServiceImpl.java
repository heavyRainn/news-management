package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CrudService<Comment> {

    @Autowired
    private CrudDao<Comment> commentDao;

    public boolean create(Comment comment) {
        return commentDao.create(comment);
    }

    public List<Comment> read() {
        return commentDao.read();
    }

    public List<Comment> read(int idNews) {
        return commentDao.read(idNews);
    }

    @Override
    public List<Comment> read(int start, int end) {
        return commentDao.read(start, end);
    }

    public boolean update(Comment comment) {
        return commentDao.update(comment);
    }

    public boolean delete(int id) {
        return commentDao.delete(id);
    }

    public boolean delete(int id, int length) {
        return commentDao.delete(id, length);
    }

    @Override
    public int totalCount() {
        return commentDao.totalCount();
    }

}
