package com.epam.newsmanagement.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.epam.newsmanagement")
@PropertySource("classpath:db.properties")
public class NewspaperRootConfig {

    @Value("${db.driver}")
    private String driver;

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;

    @Value("${db.maxActive}")
    private int maxActive;

    @Bean
    public BasicDataSource getBasicDataSource() {

        BasicDataSource bds = new BasicDataSource();

        bds.setDriverClassName(driver);
        bds.setUsername(userName);
        bds.setPassword(password);
        bds.setUrl(url);
        bds.setMaxActive(maxActive);

        return bds;

    }

}
