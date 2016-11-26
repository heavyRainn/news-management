package com.epam.newsmanagement.entity;

import javax.validation.constraints.Size;

public class Tag {

    private int id;

    @Size(min = 1, max = 250)
    private String text;

    public Tag() {

    }

    public Tag(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        return text != null ? text.equals(tag.text) : tag.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
