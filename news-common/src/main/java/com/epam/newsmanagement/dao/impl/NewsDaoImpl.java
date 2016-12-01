package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.NewsDao;
import com.epam.newsmanagement.dao.connectionpool.DataSource;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.exception.DaoException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class NewsDaoImpl implements NewsDao {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(NewsDaoImpl.class);

    private final static String SQL_GET_ALL_NEWS = String.format("SELECT N_ID,N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME FROM NEWS");
    private final static String SQL_GET_ALL_NEWS_PAGINATION = String.format("SELECT N_ID,N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME \n  from \n( select rownum rnum, a.*\n    from NEWS a\n   where rownum <= ? )\nwhere rnum >= ?");
    private final static String SQL_GET_ALL_NEWS_BY_THEME = String.format("SELECT N_ID,N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME FROM NEWS WHERE N_THEME = ?");
    private final static String SQL_GET_ALL_POPULAR_NEWS = String.format("SELECT NEWS.N_ID, NEWS.N_MAIN_TITLE,NEWS.N_SHORT_TITLE  , NEWS.N_DATE, NEWS.N_PHOTO, NEWS.N_THEME, COUNT(COM_ID) AS COUNT_COMENTS FROM NEWS JOIN COMMENTS ON NEWS.N_ID = COMMENTS.COM_ID_NEWS GROUP BY NEWS.N_ID, NEWS.N_MAIN_TITLE, NEWS.N_SHORT_TITLE , NEWS.N_DATE, NEWS.N_PHOTO, NEWS.N_THEME ORDER BY COUNT_COMENTS DESC");
    private final static String SQL_GET_SINGLE_NEWS_BY_ID = String.format("SELECT N_ID, N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME FROM NEWS WHERE N_ID = ?");
    private final static String SQL_GET_SINGLE_NEWS_BY_TITLE = String.format("SELECT N_ID, N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME FROM NEWS WHERE N_MAIN_TITLE = ?");
    private final static String SQL_GET_SINGLE_NEWS_BY_TAGS = String.format("SELECT NEWS.N_ID, NEWS.N_MAIN_TITLE, NEWS.N_SHORT_TITLE ,NEWS.N_DATE,NEWS.N_PHOTO,NEWS.N_THEME FROM NEWS JOIN NEWS_HAVE_TAGS ON NEWS_HAVE_TAGS.N_ID = NEWS.N_ID JOIN TAGS ON NEWS_HAVE_TAGS.TG_ID = TAGS.TG_ID WHERE TAGS.TG_MESSAGE IN (?) GROUP BY (NEWS.N_ID, NEWS.N_MAIN_TITLE, NEWS.N_SHORT_TITLE ,NEWS.N_DATE,NEWS.N_PHOTO,NEWS.N_THEME)");
    private final static String SQL_GET_SINGLE_NEWS_BY_AUTHORS = String.format("SELECT NEWS.N_ID, NEWS.N_MAIN_TITLE, NEWS.N_SHORT_TITLE, NEWS.N_DATE,NEWS.N_PHOTO,NEWS.N_THEME FROM NEWS JOIN NEWS_HAVE_AUTHORS ON NEWS_HAVE_AUTHORS.N_ID = NEWS.N_ID JOIN AUTHORS ON NEWS_HAVE_AUTHORS.ATR_ID = AUTHORS.ATR_ID WHERE AUTHORS.ATR_NAME IN (?) AND AUTHORS.ATR_SURNAME IN (?) GROUP BY (NEWS.N_ID, NEWS.N_MAIN_TITLE, NEWS.N_SHORT_TITLE ,NEWS.N_DATE,NEWS.N_PHOTO,NEWS.N_THEME)");
    private final static String SQL_CREATE_NEWS = String.format("INSERT INTO NEWS (N_ID, N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME) VALUES (null,?,?,?,?,?,?)");
    private final static String SQL_EDIT_NEWS = String.format("UPDATE NEWS SET N_MAIN_TITLE = ?, N_SHORT_TITLE = ? , N_NEWS_TEXT= ?, N_DATE = ?, N_PHOTO = ?, N_THEME = ? WHERE N_ID = ?");
    private final static String SQL_DELETE_NEWS = "DELETE FROM NEWS WHERE N_ID = ?";
    private final static String SQL_ADD_COMMENT_TO_NEWS = "UPDATE COMMENTS SET COM_ID_NEWS = (?) WHERE COM_ID = (?)";
    private final static String SQL_ATTACH_TAG_TO_NEWS = "INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(?,?)";
    private final static String SQL_GET_ALL_THEMES = "SELECT DISTINCT N_THEME FROM NEWS";
    private final static String SQL_GET_ALL_NEWS_BY_THEME_PAGINATION = "SELECT N_ID,N_MAIN_TITLE,N_SHORT_TITLE,N_NEWS_TEXT,N_DATE,N_PHOTO,N_THEME from ( select rownum rnum, a.* from NEWS a where rownum <= ? AND N_THEME = ? ) where rnum >= ?";
    private final static String SQL_ATTACH_AUTHOR_TO_NEWS = "INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(?,?)";

    public List<News> viewAllNews() throws DaoException {
        logger.info("NewsDaoImpl.viewAllNews()");

        List<News> newsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_NEWS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        Theme.valueOf(rs.getString(7).toUpperCase())));

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return newsList;
    }

    public boolean attachAuthor(int newsId, int authorId) {
        logger.info("NewsDaoImpl.attachAuthor(" + newsId + "" + authorId + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ATTACH_AUTHOR_TO_NEWS)) {

            ps.setInt(1, newsId);
            ps.setInt(2, authorId);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public List<News> viewAllNews(int start, int end) {
        logger.info("NewsDaoImpl.viewAllNews(" + start + ", " + end + ")");

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<News> newsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(SQL_GET_ALL_NEWS_PAGINATION);

            ps.setInt(1, end);
            ps.setInt(2, start);

            rs = ps.executeQuery();

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        Theme.valueOf(rs.getString(7).toUpperCase())));

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }

        return newsList;
    }

    @Override
    public List<News> viewAllNews(Theme theme) throws DaoException {
        logger.info("NewsDaoImpl.viewAllNews(" + theme.toString() + ")");

        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_NEWS_BY_THEME)) {

            ps.setString(1, theme.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        Theme.valueOf(rs.getString(7).toUpperCase())));

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

        return newsList;
    }

    @Override
    public List<News> viewAllNews(Theme theme, int start, int end) {
        logger.info("NewsDaoImpl.viewAllNews(" + theme.toString() + ")");

        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_NEWS_BY_THEME_PAGINATION)) {

            ps.setInt(1, end);
            ps.setString(2, theme.toString());
            ps.setInt(3, start);

            rs = ps.executeQuery();

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        Theme.valueOf(rs.getString(7).toUpperCase())));

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

        return newsList;
    }

    public List<News> viewAllPopularNews() throws DaoException {
        logger.info("NewsDaoImpl.viewAllPopularNews()");

        List<News> newsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_POPULAR_NEWS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        "",
                        rs.getDate(4),
                        rs.getString(5),
                        Theme.valueOf(rs.getString(6).toUpperCase())));

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return newsList;
    }

    public List<News> viewASingleNews(int id) throws DaoException {
        logger.info("NewsDaoImpl.viewASingleNews(" + id + ")");

        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_ID)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        Theme.valueOf(rs.getString(7).toUpperCase())));
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

        return newsList;
    }

    public List<News> viewASingleNews(String title) throws DaoException {
        logger.info("NewsDaoImpl.viewASingleNews(" + title + ")");

        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_TITLE)) {

            ps.setString(1, title);

            rs = ps.executeQuery();

            while (rs.next()) {

                newsList.add(newsFactoryMethod(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        Theme.valueOf(rs.getString(7).toUpperCase())));

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

        return newsList;
    }

    public List<News> viewASingleNews(Set<Tag> tags) throws DaoException {
        logger.info("NewsDaoImpl.viewASingleNews(" + tags.toString() + ")");

        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_TAGS)) {

            for (Tag t : tags) {

                ps.setString(1, t.getText());

                rs = ps.executeQuery();

                while (rs.next()) {

                    newsList.add(newsFactoryMethod(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            "",
                            rs.getDate(4),
                            rs.getString(5),
                            Theme.valueOf(rs.getString(6).toUpperCase())));

                }
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

        return newsList;
    }

    public List<News> viewASingleNews(List<Author> authors) throws DaoException {
        logger.info("NewsDaoImpl.viewASingleNews(" + authors.toString() + ")");

        List<News> newsList = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SINGLE_NEWS_BY_AUTHORS)) {

            for (Author author : authors) {

                ps.setString(1, author.getName());
                ps.setString(2, author.getSurname());

                rs = ps.executeQuery();

                while (rs.next()) {

                    newsList.add(newsFactoryMethod(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            "",
                            rs.getDate(4),
                            rs.getString(5),
                            Theme.valueOf(rs.getString(6).toUpperCase())));

                }
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

        return newsList;
    }

    public boolean addNews(News news) throws DaoException {
        logger.info("NewsDaoImpl.addNews(" + news.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CREATE_NEWS)) {

            ps.setString(1, news.getMainTitle());
            ps.setString(2, news.getShortTitle());
            ps.setString(3, news.getNewsText());
            ps.setDate(4, news.getDate());
            ps.setString(5, news.getPhoto());
            ps.setString(6, news.getTheme().toString());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean editNews(News news) throws DaoException {
        logger.info("NewsDaoImpl.editNews(" + news.toString() + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_EDIT_NEWS)) {

            ps.setString(1, news.getMainTitle());
            ps.setString(2, news.getShortTitle());
            ps.setString(3, news.getNewsText());
            ps.setDate(4, news.getDate());
            ps.setString(5, news.getPhoto());
            ps.setString(6, news.getTheme().toString());
            ps.setInt(7, news.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    public boolean deleteNews(int id) throws DaoException {
        logger.info("NewsDaoImpl.deleteNews(" + id + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_NEWS)) {

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
    public boolean deleteNews(int id, int length) {
        logger.info("NewsDaoImpl.deleteNews(" + id + ", " + length + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_NEWS)) {

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

    public boolean addComment(int idNews, int idComment) throws DaoException {
        logger.info("NewsDaoImpl.addNews(" + idNews + ", " + idComment + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ADD_COMMENT_TO_NEWS)) {

            ps.setInt(1, idNews);
            ps.setInt(2, idComment);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public boolean attachTagToNews(int idNews, int idTag) throws DaoException {
        logger.info("NewsDaoImpl.attachTagToNews(" + idNews + ", " + idTag + ")");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ATTACH_TAG_TO_NEWS)) {

            ps.setInt(1, idNews);
            ps.setInt(2, idTag);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    public List<Theme> viewAllThemes() {
        logger.info("NewsDaoImpl.viewAllThemes()");

        List<Theme> themes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_THEMES);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                themes.add(Theme.valueOf(rs.getString(1).toUpperCase()));

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return themes;
    }

    public int totalCount() throws DaoException {
        logger.info("NewsDaoImpl.totalCount()");
        return viewAllNews().size();
    }

    public int totalCount(Theme theme) throws DaoException {
        logger.info("NewsDaoImpl.totalCount(" + theme.toString() + ")");
        return viewAllNews(theme).size();
    }

    public News newsFactoryMethod(int id, String mainTitle, String shortTitle, String newsText, Date date, String photo, Theme theme) {
        News news = new News();

        news.setId(id);
        news.setMainTitle(mainTitle);
        news.setShortTitle(shortTitle);
        news.setNewsText(newsText);
        news.setDate(date);
        news.setPhoto(photo);
        news.setTheme(theme);

        return news;
    }

}
