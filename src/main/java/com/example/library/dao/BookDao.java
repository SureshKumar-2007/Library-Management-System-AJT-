package com.example.library.dao;

import com.example.library.model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbc;

    private RowMapper<Book> mapper = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book b = new Book();
            b.setId(rs.getInt("id"));
            b.setIsbn(rs.getString("isbn"));
            b.setTitle(rs.getString("title"));
            b.setAuthor(rs.getString("author"));
            b.setCategory(rs.getString("category"));
            b.setTotalCopies(rs.getInt("total_copies"));
            b.setAvailableCopies(rs.getInt("available_copies"));
            return b;
        }
    };

    public List<Book> findAll() {
        return jdbc.query("SELECT * FROM books ORDER BY id DESC", mapper);
    }

    public Book findById(int id) {
        return jdbc.queryForObject("SELECT * FROM books WHERE id=?", mapper, id);
    }

    public int insert(Book b) {
        return jdbc.update("INSERT INTO books(isbn,title,author,category,total_copies,available_copies) VALUES(?,?,?,?,?,?)",
            b.getIsbn(), b.getTitle(), b.getAuthor(), b.getCategory(), b.getTotalCopies(), b.getAvailableCopies());
    }

    public int update(Book b) {
        return jdbc.update("UPDATE books SET isbn=?, title=?, author=?, category=?, total_copies=?, available_copies=? WHERE id=?",
            b.getIsbn(), b.getTitle(), b.getAuthor(), b.getCategory(), b.getTotalCopies(), b.getAvailableCopies(), b.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM books WHERE id=?", id);
    }
}
