package com.epam.newsmanagement.util.search;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.entity.Theme;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class NewsSearchCriteria {

    private NewsSearchType searchType;
    private int id;
    private String title;
    private Date date;
    private Theme theme;
    private Set<Tag> tags;
    private List<Author> authors;

    public NewsSearchCriteria(NewsSearchType searchType) {
        this.searchType = searchType;
    }

    public void setSearchType(NewsSearchType searchType) {
        this.searchType = searchType;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewsSearchType getSearchType() {
        return searchType;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Theme getTheme() {
        return theme;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
