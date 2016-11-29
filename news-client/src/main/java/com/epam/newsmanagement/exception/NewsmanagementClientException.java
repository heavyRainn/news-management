package com.epam.newsmanagement.exception;

public class NewsmanagementClientException extends RuntimeException {

    public NewsmanagementClientException(String message) {
        super(message);
    }

    public NewsmanagementClientException(Exception e) {
        super(e);
    }

}
