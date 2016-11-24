package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.impl.TagServiceImpl;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {

    @InjectMocks
    TagServiceImpl tagServiceImpl;

    @Mock
    CrudDao tagDao;

    @Mock
    Tag tag;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        when(tagServiceImpl.create(tag)).thenReturn(true);
        when(tagServiceImpl.read()).thenReturn(mock(List.class));
        when(tagServiceImpl.create(tag)).thenReturn(true);
        when(tagServiceImpl.delete(1)).thenReturn(true);

        when(tagDao.create(tag)).thenReturn(true);
        when(tagDao.read()).thenReturn(mock(List.class));
        when(tagDao.create(tag)).thenReturn(true);
        when(tagDao.delete(1)).thenReturn(true);
    }

    @Test
    public void testTagServiceCreate() {
        tagServiceImpl.create(tag);
        verify(tagDao).create(tag);

        Assert.assertEquals(tagServiceImpl.create(tag), tagDao.create(tag));
    }

    @Test
    public void testTagServiceRead() throws SQLException {
        tagServiceImpl.read();
        verify(tagDao).read();

        Assert.assertEquals(tagServiceImpl.read(), tagDao.read());
    }

    @Test
    @Rollback
    public void testReadById() {
        int intStub = anyInt();

        tagServiceImpl.read(intStub);
        verify(tagDao).read(intStub);

        Assert.assertEquals(tagServiceImpl.read(intStub), tagDao.read(intStub));
    }

    @Test
    public void testTagServiceUpdate() {
        tagServiceImpl.update(tag);
        verify(tagDao).update(tag);

        Assert.assertEquals(tagServiceImpl.update(tag), tagDao.update(tag));
    }

    @Test
    public void testTagServiceDelete() {
        int intStub = anyInt();

        tagServiceImpl.delete(intStub);
        verify(tagDao).delete(intStub);

        Assert.assertEquals(tagServiceImpl.delete(intStub), tagDao.delete(intStub));
    }

    @Test
    public void testTagServiceDeleteQuery() {
        int intStub = anyInt();
        int intStub1 = anyInt();

        tagServiceImpl.delete(intStub, intStub1);
        verify(tagDao).delete(intStub, intStub1);

        Assert.assertEquals(tagServiceImpl.delete(intStub, intStub1), tagDao.delete(intStub, intStub1));
    }
}
