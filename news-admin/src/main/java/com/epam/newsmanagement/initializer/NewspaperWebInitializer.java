package com.epam.newsmanagement.initializer;

import com.epam.newsmanagement.config.NewspaperRootConfig;
import com.epam.newsmanagement.config.NewspaperSecurityConfig;
import com.epam.newsmanagement.config.NewspaperWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class NewspaperWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{NewspaperRootConfig.class, NewspaperWebConfig.class, NewspaperSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
