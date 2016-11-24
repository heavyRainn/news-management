package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.NewsDao;
import com.epam.newsmanagement.entity.*;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private CrudService<Author> authorService;

    @Autowired
    private CrudService<Tag> tagService;

    @Autowired
    private CrudService<Comment> commentService;

    public List<News> viewAllNews() {
        return decorateNews(newsDao.viewAllNews());
    }

    @Override
    public List<News> viewAllNews(int start, int end) {

        return decorateNews(newsDao.viewAllNews(start, end));
    }

    public List<News> viewAllNews(Theme theme) {
        return decorateNews(newsDao.viewAllNews(theme));
    }

    public List<News> viewAllPopularNews() {
        return decorateNews(newsDao.viewAllPopularNews());
    }

    public List<News> viewASingleNews(NewsSearchCriteria searchCriteria) {
        NewsSearchType searchType = searchCriteria.getSearchType();
        List<News> news = null;

        if (searchType == NewsSearchType.BY_ID) {
            news = newsDao.viewASingleNews(searchCriteria.getId());
        }
        if (searchType == NewsSearchType.BY_TITLE) {
            news = newsDao.viewASingleNews(searchCriteria.getTitle());
        }
        if (searchType == NewsSearchType.BY_TAGS) {
            news = newsDao.viewASingleNews(searchCriteria.getTags());
        }
        if (searchType == NewsSearchType.BY_AUTHOR) {
            news = newsDao.viewASingleNews(searchCriteria.getAuthors());
        }

        return decorateNews(news);
    }

    public boolean addNews(News news) {
        return newsDao.addNews(news);
    }

    public boolean editNews(News news) {
        return newsDao.editNews(news);
    }

    public boolean deleteNews(int id) {
        return newsDao.deleteNews(id);
    }

    public boolean deleteNews(int id, int index) {
        return newsDao.deleteNews(id, index);
    }

    public boolean addComment(int idNews, int idComment) {
        return newsDao.addComment(idNews, idComment);
    }

    public boolean attachTagToNews(int idNews, int idTag) {
        return newsDao.attachTagToNews(idNews, idTag);
    }

    public List<Theme> viewAllThemes() {
        return newsDao.viewAllThemes();
    }

    public int totalCount() {
        return newsDao.totalCount();
    }

    public int totalCount(Theme theme) {
        return newsDao.totalCount(theme);
    }

    private List<News> decorateNews(List<News> incompleteNews) {
        List<News> news = incompleteNews;

        for (News n : news) {
            int idNews = n.getId();

            n.setAuthors(authorService.read(idNews));
            n.setTags(tagService.read(idNews));
            n.setComments(commentService.read(idNews));
        }

        return news;
    }

}
