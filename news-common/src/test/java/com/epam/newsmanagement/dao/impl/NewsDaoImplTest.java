package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.config.NewspaperConfigTest;
import com.epam.newsmanagement.dao.NewsDao;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.entity.Theme;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewspaperConfigTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:db.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:tearDown.xml", type = DatabaseOperation.DELETE_ALL)
public class NewsDaoImplTest {

    @Autowired
    private NewsDao newsDao;

    private static final int NEWS_ID = 501;
    private static final int COMMENT_ID = 402;
    private static final int TAG_ID = 202;
    private static final String NEWS_TITLE = "Solution";
    private static final String NEWS_SHORT_TITLE = "Main title";
    private static final String NEWS_MAIN_TITLE = "Short title";
    private static final String NEWS_TEXT = "News text";
    private static final Theme THEME = Theme.CRIMINAL;
    private static final String TAG_MESSAGE1 = "#HOME";
    private static final String AUTHOR_NAME = "Sylwester";
    private static final String AUTHOR_SURNAME = "Stalone";

    @Test
    @Rollback
    public void testViewAllNews() {
        List<News> news = newsDao.viewAllNews();

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testViewAllNewsByTheme() {
        Theme theme = Theme.CRIMINAL;

        List<News> news = newsDao.viewAllNews(theme);

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
        Assert.assertEquals(news.get(0).getTheme(), theme);
    }

    @Test
    @Rollback
    public void testViewPopularNews() {
        List<News> news = newsDao.viewAllPopularNews();

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testViewASingleNewsById() {
        List<News> news = newsDao.viewASingleNews(NEWS_ID);

        Assert.assertFalse(news.isEmpty());
        Assert.assertEquals(news.get(0).getId(), NEWS_ID);
    }

    @Test
    @Rollback
    public void testViewASingleNewsByTitle() {
        List<News> news = newsDao.viewASingleNews(NEWS_TITLE);
        newsDao.viewASingleNews(NEWS_TITLE);

        Assert.assertFalse(news.isEmpty());
        Assert.assertEquals(news.get(0).getMainTitle(), NEWS_TITLE);
    }

    @Test
    @Rollback
    public void testViewASingleNewsByTags() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(TAG_MESSAGE1));

        List<News> news = newsDao.viewASingleNews(tags);

        Assert.assertFalse(news.isEmpty());
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testAddNews() {
        List<News> news = newsDao.viewAllNews();
        int sizeBefore = news.size();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        News news1 = new News();
        news1.setMainTitle(NEWS_MAIN_TITLE);
        news1.setShortTitle(NEWS_SHORT_TITLE);
        news1.setNewsText(NEWS_TEXT);
        news1.setDate(sqlDate);
        news1.setTheme(THEME);

        newsDao.addNews(news1);

        news = newsDao.viewAllNews();
        int sizeAfter = news.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(news.get(0) instanceof News);
    }

    @Test
    @Rollback
    public void testEditNews() {
        List<News> news = newsDao.viewAllNews();

        int idNews = news.get(0).getId();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        News newsBefore = new News();
        newsBefore.setMainTitle(NEWS_MAIN_TITLE);
        newsBefore.setShortTitle(NEWS_SHORT_TITLE);
        newsBefore.setNewsText(NEWS_TEXT);
        newsBefore.setDate(sqlDate);
        newsBefore.setTheme(THEME);
        newsBefore.setId(idNews);

        Assert.assertTrue(newsDao.editNews(newsBefore));

        news = newsDao.viewAllNews();

        News newsAfter = news.get(0);

        Assert.assertEquals(newsBefore, newsAfter);
    }

    @Test
    @Rollback
    public void testDeleteNews() {
        List<News> news = newsDao.viewAllNews();

        News news1 = news.get(0);
        int newsId = news1.getId();
        int sizeBefore = news.size();

        Assert.assertTrue(newsDao.deleteNews(newsId));

        news = newsDao.viewAllNews();

        int sizeAfter = news.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    @Rollback
    public void testDeleteNewsQuery() {
        List<News> news = newsDao.viewAllNews();

        News news1 = news.get(0);
        int newsId = news1.getId();
        int sizeBefore = news.size();

        int length = 2;

        Assert.assertTrue(newsDao.deleteNews(newsId, length));

        news = newsDao.viewAllNews();

        int sizeAfter = news.size() + length;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    @Rollback
    public void testAddComment() {
        Assert.assertTrue(newsDao.addComment(NEWS_ID, COMMENT_ID));
    }

    @Test
    @Rollback
    public void testAttachTagToNews() {
        Assert.assertTrue(newsDao.attachTagToNews(NEWS_ID, TAG_ID));
    }
}
