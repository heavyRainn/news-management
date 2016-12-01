package com.epam.newsmanagement.command;

import com.epam.newsmanagement.config.NewspaperClientRootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CommandContext {

    private static ApplicationContext instance;

    public static ApplicationContext getInstance() {
        if (instance == null) {
            synchronized (CommandContext.class) {
                if (instance == null) {
                    instance = new AnnotationConfigApplicationContext(NewspaperClientRootConfig.class);
                }
            }
        }
        return instance;
    }

}
