package com.epam.newsmanagement.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

public class News {

    private int id;

    @Size(min = 2, max = 50)
    private String mainTitle;

    @Size(min = 2, max = 250)
    private String shortTitle;

    @Size(min = 2, max = 5000)
    private String newsText;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private Date date;

    private List<Tag> tags;
    private List<Author> authors;
    private List<Comment> comments;

    @Size(max = 60)
    private String photo;

    @Size(max = 20)
    private Theme theme;


    public News() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (mainTitle != null ? !mainTitle.equals(news.mainTitle) : news.mainTitle != null) return false;
        return shortTitle != null ? shortTitle.equals(news.shortTitle) : news.shortTitle == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (mainTitle != null ? mainTitle.hashCode() : 0);
        result = 31 * result + (shortTitle != null ? shortTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", mainTitle='" + mainTitle + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", newsText='" + newsText + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                ", authors=" + authors +
                ", comments=" + comments +
                ", photo='" + photo + '\'' +
                ", theme=" + theme +
                '}';
    }
}
