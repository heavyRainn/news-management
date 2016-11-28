package com.epam.newsmanagement.exception;

public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException(String string) {
        super(string);
    }
}
