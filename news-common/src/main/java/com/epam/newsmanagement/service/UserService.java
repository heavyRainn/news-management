package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.User;

public interface UserService {

    User findByLogin(String login);

    String findRoleById(int id);

    boolean signUp(String login, String password);

}
