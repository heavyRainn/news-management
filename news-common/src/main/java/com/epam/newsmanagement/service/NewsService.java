package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;

import java.util.List;

public interface NewsService {

    List<News> viewAllNews();

    List<News> viewAllNews(int start, int end);

    List<News> viewAllNews(Theme theme);

    List<News> viewAllNews(Theme theme, int start, int end);

    List<News> viewAllPopularNews();

    List<News> viewASingleNews(NewsSearchCriteria searchCriteria);

    boolean addNews(News news);

    boolean editNews(News news);

    boolean deleteNews(int id);

    boolean deleteNews(int id, int index);

    boolean addComment(int idNews, int idComment);

    boolean attachTagToNews(int idNews, int idTag);

    List<Theme> viewAllThemes();

    int totalCount();

    int totalCount(Theme theme);

}
