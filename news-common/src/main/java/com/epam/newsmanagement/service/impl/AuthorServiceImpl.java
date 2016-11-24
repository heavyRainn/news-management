package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements CrudService<Author> {

    @Autowired
    private CrudDao<Author> authorDao;

    public boolean create(Author author) {
        return authorDao.create(author);
    }

    public List<Author> read() {
        return authorDao.read();
    }

    public List<Author> read(int idNews) {
        return authorDao.read(idNews);
    }

    @Override
    public List<Author> read(int start, int end) {
        return authorDao.read(start, end);
    }

    public boolean update(Author author) {
        return authorDao.update(author);
    }

    public boolean delete(int id) {
        return authorDao.delete(id);
    }

    public boolean delete(int id, int length) {
        return authorDao.delete(id, length);
    }

    @Override
    public int totalCount() {
        return authorDao.totalCount();
    }

}
