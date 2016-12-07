package com.epam.newsmanagement.initializer;

import com.epam.newsmanagement.config.NewspaperRootConfig;
import com.epam.newsmanagement.config.NewspaperSecurityConfig;
import com.epam.newsmanagement.config.NewspaperSwaggerConfig;
import com.epam.newsmanagement.config.NewspaperWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class NewspaperWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String LOCATION = "C:\\Users\\Andrei_Fiodarau\\IdeaProjects\\news-management\\news-admin\\src\\main\\webapp\\resources\\images";
    private static final long MAX_FILE_SIZE = 5242880;
    private static final long MAX_REQUEST_SIZE = 20971520;
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{NewspaperRootConfig.class, NewspaperWebConfig.class,
                NewspaperSecurityConfig.class, NewspaperSwaggerConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }

}
