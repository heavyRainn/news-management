package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.entity.Theme;

import java.util.List;
import java.util.Set;

public interface NewsDao {

    List<News> viewAllNews();

    List<News> viewAllNews(int start, int end);

    List<News> viewAllNews(Theme theme);

    List<News> viewAllNews(Theme theme, int start, int end);

    List<News> viewAllPopularNews();

    List<News> viewASingleNews(int id);

    List<News> viewASingleNews(String title);

    List<News> viewASingleNews(Set<Tag> tags);

    List<News> viewASingleNews(List<Author> authors);

    boolean addNews(News news);

    boolean editNews(News news);

    boolean deleteNews(int id);

    boolean deleteNews(int id, int length);

    boolean addComment(int idNews, int idComment);

    boolean attachTagToNews(int idNews, int idTag);

    boolean attachAuthor(int newsId, int authorId);

    List<Theme> viewAllThemes();

    int totalCount();

    int totalCount(Theme theme);
}
