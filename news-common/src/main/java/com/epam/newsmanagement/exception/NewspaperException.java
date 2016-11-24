package com.epam.newsmanagement.exception;

public class NewspaperException extends RuntimeException {

    public NewspaperException(Throwable exception) {
        super(exception);
    }

    public NewspaperException(String string) {
        super(string);
    }

    public NewspaperException(String message, Exception e) {
        super(message, e);
    }
}
