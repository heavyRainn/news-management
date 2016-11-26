package com.epam.newsmanagement.exception;

public class DomParserException extends Exception {

    public DomParserException(String message) {
        super(message);
    }

    public DomParserException(Exception e) {
        super(e);
    }

    public DomParserException(String message, Exception e) {
        super(message, e);
    }
}
