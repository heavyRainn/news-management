package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.NewsDao;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.service.impl.NewsServiceImpl;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @InjectMocks
    NewsServiceImpl newsServiceImpl;

    @Mock
    NewsDao newsDao;

    @Mock
    News news;

    @Mock
    List list;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        when(newsServiceImpl.viewAllNews()).thenReturn(list);
        when(newsServiceImpl.viewAllPopularNews()).thenReturn(list);
        when(newsServiceImpl.addNews(news)).thenReturn(true);
        when(newsServiceImpl.editNews(news)).thenReturn(true);
        when(newsServiceImpl.deleteNews(anyInt())).thenReturn(true);
        when(newsServiceImpl.addComment(anyInt(), anyInt())).thenReturn(true);
        when(newsServiceImpl.attachTagToNews(anyInt(), anyInt())).thenReturn(true);
        when(newsServiceImpl.totalCount()).thenReturn(3);
        when(newsServiceImpl.totalCount(Theme.CRIMINAL)).thenReturn(3);

        when(newsDao.viewAllNews()).thenReturn(list);
        when(newsDao.viewAllPopularNews()).thenReturn(list);
        when(newsDao.addNews(news)).thenReturn(true);
        when(newsDao.editNews(news)).thenReturn(true);
        when(newsDao.deleteNews(anyInt())).thenReturn(true);
        when(newsDao.addComment(anyInt(), anyInt())).thenReturn(true);
        when(newsDao.attachTagToNews(anyInt(), anyInt())).thenReturn(true);
        when(newsDao.totalCount()).thenReturn(3);
        when(newsDao.totalCount(Theme.CRIMINAL)).thenReturn(3);
    }

    @Test
    public void testNewsServiceViewASingleNewsById() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        int intStub = 3;
        searchCriteria.setId(intStub);

        newsServiceImpl.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(searchCriteria.getId());

        Assert.assertEquals(newsServiceImpl.viewASingleNews(searchCriteria), newsDao.viewASingleNews(searchCriteria.getId()));
    }


    @Test
    public void testNewsServiceViewASingleNewsByTags() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_TAGS);
        searchCriteria.setTags(any(HashSet.class));

        newsServiceImpl.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(searchCriteria.getTags());

        Assert.assertEquals(newsServiceImpl.viewASingleNews(searchCriteria), newsDao.viewASingleNews(searchCriteria.getTags()));
    }

    @Test
    public void testNewsServiceViewASingleNewsByAuthors() {
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(NewsSearchType.BY_AUTHOR);
        searchCriteria.setAuthors(list);

        newsServiceImpl.viewASingleNews(searchCriteria);
        verify(newsDao).viewASingleNews(searchCriteria.getAuthors());

        Assert.assertEquals(newsServiceImpl.viewASingleNews(searchCriteria), newsDao.viewASingleNews(searchCriteria.getAuthors()));
    }

    @Test
    public void testNewsServiceAddNews() {
        newsServiceImpl.addNews(news);
        verify(newsDao).addNews(news);

        Assert.assertEquals(newsServiceImpl.addNews(news), newsDao.addNews(news));
    }

    @Test
    public void testNewsServiceEditNews() {
        newsServiceImpl.editNews(news);
        verify(newsDao).editNews(news);

        Assert.assertEquals(newsServiceImpl.editNews(news), newsDao.editNews(news));
    }

    @Test
    public void testNewsServiceDeleteNews() {
        int intStub = anyInt();

        newsServiceImpl.deleteNews(intStub);
        verify(newsDao).deleteNews(intStub);

        Assert.assertEquals(newsServiceImpl.deleteNews(intStub), newsDao.deleteNews(intStub));
    }

    @Test
    public void testNewsServiceDeleteNewsQuery() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsServiceImpl.deleteNews(intStub, intStub1);
        verify(newsDao).deleteNews(intStub, intStub1);

        Assert.assertEquals(newsServiceImpl.deleteNews(intStub, intStub1), newsDao.deleteNews(intStub, intStub1));
    }

    @Test
    public void testNewsServiceAddComment() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsServiceImpl.addComment(intStub, intStub1);
        verify(newsDao).addComment(intStub, intStub1);

        Assert.assertEquals(newsServiceImpl.addComment(intStub, intStub1), newsDao.addComment(intStub, intStub1));
    }

    @Test
    public void testNewsServiceAttachTagToNews() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        newsServiceImpl.attachTagToNews(intStub, intStub1);
        verify(newsDao).attachTagToNews(intStub, intStub1);

        Assert.assertEquals(newsServiceImpl.attachTagToNews(intStub, intStub1), newsDao.attachTagToNews(intStub, intStub1));
    }

    @Test
    public void testNewsServiceTotalCount() {
        newsServiceImpl.totalCount();
        verify(newsDao).totalCount();

        Assert.assertEquals(newsServiceImpl.totalCount(), newsDao.totalCount());
    }

    @Test
    public void testNewsServiceTotalCountByTheme() {
        newsServiceImpl.totalCount(Theme.CRIMINAL);
        verify(newsDao).totalCount(Theme.CRIMINAL);

        Assert.assertEquals(newsServiceImpl.totalCount(Theme.CRIMINAL), newsDao.totalCount(Theme.CRIMINAL));
    }

}