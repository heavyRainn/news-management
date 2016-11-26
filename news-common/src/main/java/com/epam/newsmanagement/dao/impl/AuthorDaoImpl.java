package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.dao.connectionpool.DataSource;
import com.epam.newsmanagement.exception.DaoException;
import com.epam.newsmanagement.entity.Author;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AuthorDaoImpl implements CrudDao<Author> {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(AuthorDaoImpl.class);

    private final static String SQL_GET_ALL_AUTHORS = "SELECT DISTINCT ATR_ID,ATR_NAME,ATR_SURNAME FROM AUTHORS";
    private final static String SQL_GET_ALL_AUTHORS_OF_ONE_NEWS = String.format("SELECT DISTINCT AUTHORS.ATR_ID,AUTHORS.ATR_NAME,AUTHORS.ATR_SURNAME FROM AUTHORS INNER JOIN NEWS_HAVE_AUTHORS ON AUTHORS.ATR_ID = NEWS_HAVE_AUTHORS.ATR_ID WHERE  NEWS_HAVE_AUTHORS.N_ID = ?");
    private final static String SQL_CREATE_AUTHOR = "INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (null,?,?)";
    private final static String SQL_UPDATE_AUTHOR = "UPDATE AUTHORS SET ATR_NAME = ?, ATR_SURNAME = ? WHERE ATR_ID = ?";
    private final static String SQL_DELETE_AUTHOR = "DELETE FROM AUTHORS WHERE ATR_ID = ?";
    private final static String SQL_GET_ALL_AUTHORS_PAGINATION = "SELECT ATR_ID,ATR_NAME,ATR_SURNAME from ( select rownum rnum, a.* from AUTHORS a where rownum <= ? ) where rnum >= ?";

    public boolean create(Author author) throws DaoException {
        logger.info("AuthorDaoImpl.create(" + author.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_AUTHOR)) {

            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Author> read() throws DaoException {
        logger.info("AuthorDaoImpl.read()");

        List<Author> authors = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_AUTHORS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Author author = new Author(rs.getString(2), rs.getString(3));
                author.setId(rs.getInt(1));

                authors.add(author);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return authors;
    }

    @Override
    public List<Author> read(int idNews) throws DaoException {
        logger.info("AuthorDaoImpl.read(" + idNews + ")");

        List<Author> authors = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_AUTHORS_OF_ONE_NEWS)) {

            ps.setInt(1, idNews);

            rs = ps.executeQuery();

            while (rs.next()) {

                Author author = new Author(rs.getString(2), rs.getString(3));
                author.setId(rs.getInt(1));

                authors.add(author);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return authors;
    }

    @Override
    public List<Author> read(int start, int end) {
        logger.info("AuthorDaoImpl.read(" + start + "," + end + ")");

        List<Author> authors = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_AUTHORS_PAGINATION)) {

            ps.setInt(1, end);
            ps.setInt(2, start);

            rs = ps.executeQuery();

            while (rs.next()) {

                Author author = new Author(rs.getString(2), rs.getString(3));
                author.setId(rs.getInt(1));

                authors.add(author);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return authors;
    }


    public boolean update(Author author) throws DaoException {
        logger.info("AuthorDaoImpl.update(" + author.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_AUTHOR)) {

            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setInt(3, author.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean delete(int id) throws DaoException {
        logger.info("AuthorDaoImpl.delete(" + id + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_AUTHOR)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public boolean delete(int id, int length) {
        logger.info("AuthorDaoImpl.delete(" + id + ", " + length + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_AUTHOR)) {

            int flag = 0;

            for (int idNews = id; idNews < id + length; idNews++) {
                ps.setInt(1, idNews);

                if (ps.executeUpdate() > 0) {
                    flag++;
                }

                if (flag == length) {
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public int totalCount() {
        return read().size();
    }
}
