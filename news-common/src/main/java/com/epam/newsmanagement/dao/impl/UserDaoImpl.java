package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.UserDao;
import com.epam.newsmanagement.dao.connectionpool.DataSource;
import com.epam.newsmanagement.entity.User;
import com.epam.newsmanagement.exception.DaoException;
import com.epam.newsmanagement.util.Coder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final String AUTHENTICATE = "SELECT DISTINCT UR_ID,UR_LOGIN,UR_PASSWORD FROM USERS WHERE UR_LOGIN=(?) ";
    private static final String SIGN_UP = "INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (null,?,?)";
    private static final String SIGN_UP_ROLE = "INSERT INTO USER_HAVE_ROLE (UR_ID, ROLE_ID) VALUES (?, 1)";
    private static final String FIND_ROLE_BY_ID = "SELECT DISTINCT ROLE_VALUE From USER_ROLES INNER JOIN USER_HAVE_ROLE ON USER_HAVE_ROLE.ROLE_ID = USER_ROLES.ROLE_ID WHERE USER_HAVE_ROLE.UR_ID = ?";

    public User findByLogin(String login) throws DaoException {
        logger.info("UserDaoImpl.findByLogin(" + login + ")");

        ResultSet rs = null;
        User user = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(AUTHENTICATE)) {

            ps.setString(1, login);

            rs = ps.executeQuery();

            while (rs.next()) {

                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return user;
    }

    @Override
    public String findRoleById(int id) {
        logger.info("UserDaoImpl.findRoleById(" + id + ")");

        String role = null;

        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ROLE_BY_ID)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                role = rs.getString(1);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return role;
    }

    public boolean signUp(String login, String password) throws DaoException {
        logger.info("UserDaoImpl.signUp(" + login + ", " + password + ")");

        PreparedStatement ps = null;
        ResultSet rs;
        int idUser = 0;

        try (Connection connection = dataSource.getConnection()) {

            ps = connection.prepareStatement(SIGN_UP);

            ps.setString(1, login);
            ps.setString(2, Coder.getHashedPassword(password));

            ps.executeUpdate();

            ps = connection.prepareStatement(AUTHENTICATE);

            ps.setString(1, login);

            rs = ps.executeQuery();

            while (rs.next()) {
                idUser = rs.getInt(1);
            }

            rs.close();

            ps = connection.prepareStatement(SIGN_UP_ROLE);

            ps.setInt(1, idUser);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return false;
    }

}
