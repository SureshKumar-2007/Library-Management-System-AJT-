package com.example.library.dao;

import com.example.library.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;

@Repository
public class LoanDao {

    @Autowired private JdbcTemplate jdbc;

    private final RowMapper<Loan> mapper = (rs, i) -> {
        Loan l = new Loan();
        l.setId(rs.getInt("id"));
        l.setBookId(rs.getInt("book_id"));
        l.setMemberId(rs.getInt("member_id"));
        l.setIssueDate(rs.getDate("issue_date").toLocalDate());
        l.setDueDate(rs.getDate("due_date").toLocalDate());
        Date r = rs.getDate("return_date");
        l.setReturnDate(r == null ? null : r.toLocalDate());
        l.setFineAmount(rs.getBigDecimal("fine_amount"));
        return l;
    };

    public List<Loan> findAll() { return jdbc.query("SELECT * FROM loans ORDER BY id DESC", mapper); }
    public List<Loan> findOpen(){ return jdbc.query("SELECT * FROM loans WHERE return_date IS NULL ORDER BY id DESC", mapper); }

    public int issue(Integer bookId, Integer memberId, LocalDate issueDate, LocalDate dueDate) {
        return jdbc.update("INSERT INTO loans(book_id, member_id, issue_date, due_date) VALUES(?,?,?,?)",
                bookId, memberId, Date.valueOf(issueDate), Date.valueOf(dueDate));
    }

    public int markReturn(Integer loanId, LocalDate returnDate, BigDecimal fine) {
        return jdbc.update("UPDATE loans SET return_date=?, fine_amount=? WHERE id=?",
                Date.valueOf(returnDate), fine, loanId);
    }

    public int decAvailable(Integer bookId) {
        return jdbc.update("UPDATE books SET available_copies = available_copies - 1 WHERE id=? AND available_copies>0", bookId);
    }

    public int incAvailable(Integer bookId) {
        return jdbc.update("UPDATE books SET available_copies = available_copies + 1 WHERE id=?", bookId);
    }

    public Loan findById(Integer id) {
        return jdbc.queryForObject("SELECT * FROM loans WHERE id=?", mapper, id);
    }
}
