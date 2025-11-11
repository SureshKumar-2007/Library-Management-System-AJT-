package com.example.library.dao;

import com.example.library.model.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    @Autowired
    private JdbcTemplate jdbc;

    private RowMapper<Member> mapper = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member m = new Member();
            m.setId(rs.getInt("id"));
            m.setFullName(rs.getString("full_name"));
            m.setEmail(rs.getString("email"));
            m.setPhone(rs.getString("phone"));
            return m;
        }
    };

    public List<Member> findAll() {
        return jdbc.query("SELECT * FROM members ORDER BY id DESC", mapper);
    }

    public Member findById(int id) {
        return jdbc.queryForObject("SELECT * FROM members WHERE id=?", mapper, id);
    }

    public int insert(Member m) {
        return jdbc.update("INSERT INTO members(full_name,email,phone) VALUES(?,?,?)",
            m.getFullName(), m.getEmail(), m.getPhone());
    }

    public int update(Member m) {
        return jdbc.update("UPDATE members SET full_name=?, email=?, phone=? WHERE id=?",
            m.getFullName(), m.getEmail(), m.getPhone(), m.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM members WHERE id=?", id);
    }
}
