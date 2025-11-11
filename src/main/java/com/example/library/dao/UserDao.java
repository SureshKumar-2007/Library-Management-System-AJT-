package com.example.library.dao;

import com.example.library.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbc;

    private RowMapper<User> mapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password_hash"));
            u.setRole(rs.getString("role"));
            return u;
        }
    };

    public User findByUsername(String username) {
        List<User> list = jdbc.query("SELECT * FROM users WHERE username=?", mapper, username);
        return list.isEmpty() ? null : list.get(0);
    }
}
