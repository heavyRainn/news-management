package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.UserDao;
import com.epam.newsmanagement.entity.User;
import com.epam.newsmanagement.service.impl.UserServiceImpl;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserDao userDao;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();
    }

    @Test
    public void testUserServiceLogin() {
        String login = any(String.class);

        userServiceImpl.findByLogin(login);
        verify(userDao).findByLogin(login);

        Assert.assertEquals(userServiceImpl.findByLogin(login), userDao.findByLogin(login));
    }

    @Test
    public void testUserServiceSignUp() {
        String login = any(String.class);
        String password = any(String.class);

        userServiceImpl.signUp(login, password);
        verify(userDao).signUp(login, password);

        Assert.assertEquals(userServiceImpl.signUp(login, password), userDao.signUp(login, password));
    }

}
