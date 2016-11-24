package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements CrudService<Tag> {

    @Autowired
    private CrudDao<Tag> tagDao;

    public boolean create(Tag tag) {
        return tagDao.create(tag);
    }

    public List<Tag> read() {
        return tagDao.read();
    }

    public List<Tag> read(int idNews) {
        return tagDao.read(idNews);
    }

    @Override
    public List<Tag> read(int start, int end) {
        return tagDao.read(start, end);
    }

    public boolean update(Tag tag) {
        return tagDao.update(tag);
    }

    public boolean delete(int id) {
        return tagDao.delete(id);
    }

    public boolean delete(int id, int length) {
        return tagDao.delete(id, length);
    }

    @Override
    public int totalCount() {
        return tagDao.totalCount();
    }

}
