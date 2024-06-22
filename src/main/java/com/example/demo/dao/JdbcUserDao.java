package com.example.demo.dao;

import com.example.demo.exception.DaoException;
import com.example.demo.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByUsername(String username) {

        if (username == null) {
            username = "";
        }

        User user = null;
        final String sql = "SELECT user_id, username, password_hash, role " +
                           "FROM users " +
                           "WHERE username = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return user;
    }

    private User mapRowToUser(SqlRowSet rs) {
        return new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("role")
        );
    }
}
