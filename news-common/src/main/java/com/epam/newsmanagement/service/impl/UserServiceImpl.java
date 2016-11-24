package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.UserDao;
import com.epam.newsmanagement.entity.User;
import com.epam.newsmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public String findRoleById(int id) {
        return userDao.findRoleById(id);
    }

    public boolean signUp(String login, String password) {
        return userDao.signUp(login, password);
    }

}
