package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.config.NewspaperConfigTest;
import com.epam.newsmanagement.dao.CrudDao;
import com.epam.newsmanagement.entity.Author;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewspaperConfigTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:db.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:tearDown.xml", type = DatabaseOperation.DELETE_ALL)
public class AuthorDaoImplTest {

    @Autowired
    private CrudDao<Author> authorDao;

    private static final String NAME_CREATE = "Yaehal";
    private static final String SURNAME_CREATE = "Natelege";
    private static final String NAME_UPDATE = "Zigmoond";
    private static final String SURNAME_UPDATE = "Freid";
    private static final int NEWS_ID = 501;

    @Test
    @Rollback
    public void testCreate() {
        List<Author> authors = authorDao.read();
        int sizeBefore = authors.size();

        Author author = new Author(NAME_CREATE, SURNAME_CREATE);
        authorDao.create(author);

        authors = authorDao.read();
        int sizeAfter = authors.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(authors.get(0) instanceof Author);
    }

    @Test
    @Rollback
    public void testRead() {
        List<Author> authors = authorDao.read();

        Assert.assertFalse(authors.isEmpty());
        Assert.assertTrue(authors.get(0) instanceof Author);
    }

    @Test
    @Rollback
    public void testReadById() {
        List<Author> authors = authorDao.read(NEWS_ID);

        Assert.assertFalse(authors.isEmpty());
        Assert.assertTrue(authors.get(0) instanceof Author);
    }

    @Test
    @Rollback
    public void testUpdate() {
        List<Author> authors = authorDao.read();

        Author authorBefore = new Author(NAME_UPDATE, SURNAME_UPDATE);
        authorBefore.setId(authors.get(0).getId());

        Assert.assertTrue(authorDao.update(authorBefore));

        authors = authorDao.read();

        Author authorAfter = authors.get(0);

        Assert.assertEquals(authorBefore, authorAfter);
    }

    @Test
    @Rollback
    public void testDelete() {
        List<Author> authors = authorDao.read();

        Author author = authors.get(0);
        int authorId = author.getId();
        int sizeBefore = authors.size();

        Assert.assertTrue(authorDao.delete(authorId));

        authors = authorDao.read();

        int sizeAfter = authors.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    @Rollback
    public void testDeleteQuery() {
        List<Author> authors = authorDao.read();

        Author author = authors.get(0);
        int authorId = author.getId();
        int sizeBefore = authors.size();

        int length = 2;

        Assert.assertTrue(authorDao.delete(authorId, length));

        authors = authorDao.read();

        int sizeAfter = authors.size() + length;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }
}
