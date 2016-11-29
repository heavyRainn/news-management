package com.epam.newsmanagement.exception;

public class NewsNotFoundException extends NewsmanagementAdminException {

    public NewsNotFoundException(String string) {
        super(string);
    }
}
