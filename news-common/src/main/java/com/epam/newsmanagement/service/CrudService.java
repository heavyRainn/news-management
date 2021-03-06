package com.epam.newsmanagement.service;

import java.util.List;

public interface CrudService<T> {

    boolean create(T t);

    List<T> read();

    List<T> read(int idNews);

    List<T> read(int start, int end);

    boolean update(T t);

    boolean delete(int id);

    boolean delete(int id, int length);

    int totalCount();

}
