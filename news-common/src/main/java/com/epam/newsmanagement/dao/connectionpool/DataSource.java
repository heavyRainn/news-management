package com.epam.newsmanagement.dao.connectionpool;

import com.epam.newsmanagement.exception.DataSourceException;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource extends DriverManagerDataSource {

    @Autowired
    private BasicDataSource ds;

    public Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

}
