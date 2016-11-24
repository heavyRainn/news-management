package com.epam.newsmanagement.exception;

public class JspContextException extends RuntimeException{

    public JspContextException(String message) {
        super(message);
    }

    public JspContextException(Exception e) {
        super(e);
    }

    public JspContextException(String message, Exception e) {
        super(message, e);
    }
}
