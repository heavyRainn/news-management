package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.dao.connectionpool.DataSource;
import com.epam.newsmanagement.exception.DaoException;
import com.epam.newsmanagement.entity.Comment;
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
public class CommentDaoImpl implements CrudDao<Comment> {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(CommentDaoImpl.class);

    private final static String SQL_CREATE_COMMENT = "INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (null,?,?,?,?)";
    private final static String SQL_GET_ALL_COMMENTS = "SELECT COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS FROM COMMENTS";
    private final static String SQL_GET_ALL_COMMENTS_OF_ONE_NEWS = "SELECT COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS FROM COMMENTS WHERE COM_ID_NEWS = (?)";
    private final static String SQL_UPDATE_COMMENT = "UPDATE COMMENTS SET COM_MESSAGE = ?, COM_DATE = SYSDATE, COM_ID_AUTHOR = ?,COM_ID_NEWS = ? WHERE COM_ID = ?";
    private final static String SQL_DELETE_COMMENT = "DELETE FROM COMMENTS WHERE COM_ID = ?";
    private final static String SQL_GET_ALL_COMMENTS_PAGINATION = String.format("SELECT COM_ID,COM_MESSAGE,COM_DATE  from ( select rownum rnum, a.* from COMMENTS a where rownum <= ? )where rnum >= ?;");

    public boolean create(Comment comment) throws DaoException {
        logger.info("CommentDaoImpl.create(" + comment.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_COMMENT)) {

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            ps.setString(1, comment.getText());
            ps.setDate(2, sqlDate);
            ps.setInt(3, comment.getUserId());
            ps.setInt(4, comment.getNewsId());


            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Comment> read() throws DaoException {
        logger.info("CommentDaoImpl.read()");

        List<Comment> comments = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_COMMENTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Comment comment = new Comment(rs.getString(2), rs.getDate(3));
                comment.setId(rs.getInt(1));
                comment.setUserId(rs.getInt(4));
                comment.setNewsId(rs.getInt(5));

                comments.add(comment);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return comments;
    }

    @Override
    public List<Comment> read(int idNews) throws DaoException {
        logger.info("CommentDaoImpl.read(" + idNews + ")");

        List<Comment> comments = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_COMMENTS_OF_ONE_NEWS)) {

            ps.setInt(1, idNews);

            rs = ps.executeQuery();

            while (rs.next()) {

                Comment comment = new Comment(rs.getString(2), rs.getDate(3));
                comment.setId(rs.getInt(1));
                comment.setUserId(rs.getInt(4));
                comment.setNewsId(rs.getInt(5));

                comments.add(comment);

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

        return comments;
    }

    @Override
    public List<Comment> read(int start, int end) {
        logger.info("CommentDaoImpl.read(" + start + "," + end + ")");

        List<Comment> comments = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_COMMENTS_PAGINATION)) {

            ps.setInt(1, end);
            ps.setInt(2, start);

            rs = ps.executeQuery();

            while (rs.next()) {

                Comment comment = new Comment(rs.getString(2), rs.getDate(3));
                comment.setId(rs.getInt(1));
                comment.setUserId(rs.getInt(4));
                comment.setNewsId(rs.getInt(5));

                comments.add(comment);

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

        return comments;
    }

    public boolean update(Comment comment) throws DaoException {
        logger.info("CommentDaoImpl.update(" + comment.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_COMMENT)) {

            ps.setString(1, comment.getText());
            ps.setInt(2, comment.getUserId());
            ps.setInt(3, comment.getNewsId());
            ps.setInt(4, comment.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean delete(int id) throws DaoException {
        logger.info("CommentDaoImpl.delete(" + id + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_COMMENT)) {

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
        logger.info("CommentDaoImpl.delete(" + id + ", " + length + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_COMMENT)) {

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
