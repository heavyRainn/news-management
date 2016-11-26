package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.dao.connectionpool.DataSource;
import com.epam.newsmanagement.exception.DaoException;
import com.epam.newsmanagement.entity.Tag;
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
public class TagDaoImpl implements CrudDao<Tag> {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(TagDaoImpl.class);

    private final static String SQL_CREATE_TAG = "INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (null, ?)";
    private final static String SQL_GET_ALL_TAGS = "SELECT DISTINCT TG_ID,TG_MESSAGE FROM TAGS";
    private final static String SQL_GET_NEWS_TAGS = "SELECT DISTINCT TAGS.TG_ID,TAGS.TG_MESSAGE FROM TAGS INNER JOIN NEWS_HAVE_TAGS ON TAGS.TG_ID = NEWS_HAVE_TAGS.TG_ID WHERE NEWS_HAVE_TAGS.N_ID = ?";
    private final static String SQL_UPDATE_TAG = "UPDATE TAGS SET TG_MESSAGE = ? WHERE TG_ID = ?";
    private final static String SQL_DELETE_TAG = "DELETE FROM TAGS WHERE TG_ID = ?";
    private final static String SQL_GET_TAGS_PAGINATION = String.format("SELECT TG_ID,TG_MESSAGE  from ( select rownum rnum, a.* from TAGS a where rownum <= ? ) where rnum >= ?");

    public boolean create(Tag tag) throws DaoException {
        logger.info("TagDaoImpl.create(" + tag.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_TAG)) {

            ps.setString(1, tag.getText());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Tag> read() throws DaoException {
        logger.info("TagDaoImpl.read()");

        List<Tag> tags = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_TAGS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Tag tag = new Tag(rs.getString(2));
                tag.setId(rs.getInt(1));

                tags.add(tag);

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return tags;
    }

    @Override
    public List<Tag> read(int idNews) throws DaoException {
        logger.info("TagDaoImpl.read(" + idNews + ")");

        List<Tag> tags = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_NEWS_TAGS)) {

            ps.setInt(1, idNews);

            rs = ps.executeQuery();

            while (rs.next()) {

                Tag tag = new Tag(rs.getString(2));
                tag.setId(rs.getInt(1));

                tags.add(tag);

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

        return tags;
    }

    @Override
    public List<Tag> read(int start, int end) {
        logger.info("TagDaoImpl.read(" + start + "," + end + ")");

        List<Tag> tags = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_TAGS_PAGINATION)) {

            ps.setInt(1, end);
            ps.setInt(2, start);

            rs = ps.executeQuery();

            while (rs.next()) {

                Tag tag = new Tag(rs.getString(2));
                tag.setId(rs.getInt(1));

                tags.add(tag);

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

        return tags;
    }

    public boolean update(Tag tag) throws DaoException {
        logger.info("TagDaoImpl.update(" + tag.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TAG)) {

            ps.setString(1, tag.getText());
            ps.setInt(2, tag.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean delete(int id) throws DaoException {
        logger.info("TagDaoImpl.delete(" + id + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_TAG)) {

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
        logger.info("TagDaoImpl.delete(" + id + ", " + length + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_TAG)) {

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
