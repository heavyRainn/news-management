package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.config.NewspaperConfigTest;
import com.epam.newsmanagement.dao.UserDao;
import com.epam.newsmanagement.entity.User;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewspaperConfigTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:db.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:tearDown.xml", type = DatabaseOperation.DELETE_ALL)
public class UserDaoImplTest {

    @Autowired
    private UserDaoImpl userDao;

    private static final String LOGIN_EXISTS = "serega345";
    private static final String LOGIN_SIGN_UP = "Varfalamei";
    private static final String PASSWORD_SIGN_UP = "530";

    @Test
    @Rollback
    public void testAuthenticateReturnTrue() {
        User user = userDao.findByLogin(LOGIN_EXISTS);
        Assert.assertTrue(user.getLogin().equals(LOGIN_EXISTS));
    }

    @Test
    @Rollback
    public void testSignUpReturnTrue() {
        Assert.assertTrue(userDao.signUp(LOGIN_SIGN_UP, PASSWORD_SIGN_UP));
    }


}
