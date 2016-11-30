package com.epam.newsmanagement.command;

import com.epam.newsmanagement.config.NewspaperClientRootConfig;
import com.epam.newsmanagement.exception.CommandException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(NewspaperClientRootConfig.class);

    String execute(HttpServletRequest request) throws CommandException;

    default Object getBean(Class clazz){
        return ctx.getBean(clazz);
    }

}
