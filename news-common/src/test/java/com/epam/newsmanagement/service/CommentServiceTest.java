package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.service.impl.CommentServiceImpl;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    CrudDao commentDao;

    @Mock
    Comment comment;

    @Before
    public void setUp() {
        BasicConfigurator.configure();

        when(commentService.create(comment)).thenReturn(true);
        when(commentService.read()).thenReturn(mock(List.class));
        when(commentService.create(comment)).thenReturn(true);
        when(commentService.delete(1)).thenReturn(true);

        when(commentDao.create(comment)).thenReturn(true);
        when(commentDao.read()).thenReturn(mock(List.class));
        when(commentDao.create(comment)).thenReturn(true);
        when(commentDao.delete(1)).thenReturn(true);
    }

    @Test
    public void testCommentServiceCreate() {
        commentService.create(comment);
        verify(commentDao).create(comment);

        Assert.assertEquals(commentService.create(comment), commentDao.create(comment));
    }

    @Test
    public void testCommentServiceRead() throws SQLException {
        commentService.read();
        verify(commentDao).read();

        Assert.assertEquals(commentService.read(), commentDao.read());
    }

    @Test
    public void testCommentServiceReadById() throws SQLException {
        int intStub = anyInt();

        commentService.read(intStub);
        verify(commentDao).read(intStub);

        Assert.assertEquals(commentService.read(intStub), commentDao.read(intStub));
    }

    @Test
    public void testCommentServiceUpdate() {
        commentService.update(comment);
        verify(commentDao).update(comment);

        Assert.assertEquals(commentService.update(comment), commentDao.update(comment));
    }

    @Test
    public void testCommentServiceDelete() {
        int intStub = anyInt();

        commentService.delete(intStub);
        verify(commentDao).delete(intStub);

        Assert.assertEquals(commentService.delete(intStub), commentDao.delete(intStub));
    }

    @Test
    public void testCommentServiceDeleteQuery() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        commentService.delete(intStub, intStub1);
        verify(commentDao).delete(intStub, intStub1);

        Assert.assertEquals(commentService.delete(intStub, intStub1), commentDao.delete(intStub, intStub1));
    }
}
