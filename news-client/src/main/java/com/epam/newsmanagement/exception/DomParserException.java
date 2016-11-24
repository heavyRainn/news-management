package com.epam.newsmanagement.exception;

public class DomParserException extends Exception {

    private static final long serialVersionUID = 1L;

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
