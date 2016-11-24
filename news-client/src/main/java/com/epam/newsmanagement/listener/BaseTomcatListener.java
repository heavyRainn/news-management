package com.epam.newsmanagement.listener;

import com.epam.newsmanagement.exception.JspContextException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class BaseTomcatListener implements ServletContextListener {

    public static final String JSP_RUNTIME_CONTEXT = "org.apache.jasper.compiler.JspRuntimeContext";

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(JSP_RUNTIME_CONTEXT);
        } catch (Exception e) {
            throw new JspContextException(e);
        }
    }
}
