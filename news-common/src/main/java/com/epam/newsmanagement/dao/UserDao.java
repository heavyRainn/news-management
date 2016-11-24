package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.entity.User;

public interface UserDao {

    User findByLogin(String login);

    String findRoleById(int id);

    boolean signUp(String login, String password);

}
