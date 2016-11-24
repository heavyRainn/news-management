package com.epam.newsmanagement.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

public class Comment {

    private int id;

    @NotNull
    @Size(max = 250)
    private String text;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private Date date;

    private int userId;

    private int newsId;

    public Comment(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (userId != comment.userId) return false;
        if (newsId != comment.newsId) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        return date != null ? date.equals(comment.date) : comment.date == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + newsId;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                ", newsId=" + newsId +
                '}';
    }
}
