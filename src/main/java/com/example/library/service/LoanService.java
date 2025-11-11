package com.example.library.service;

import com.example.library.dao.LoanDao;
import com.example.library.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanDao loanDao;

    public List<Loan> listAll() {
        return loanDao.findAll();
    }

    public List<Loan> listOpen() {
        return loanDao.findOpen();
    }

    public boolean issue(Integer bookId, Integer memberId) {
        int updated = loanDao.decAvailable(bookId);
        if (updated == 0) {
            return false; // no copies left
        }
        LocalDate today = LocalDate.now();
        loanDao.issue(bookId, memberId, today, today.plusDays(14));
        return true;
    }

    public void returnBook(Integer loanId) {
        Loan l = loanDao.findById(loanId);
        LocalDate today = LocalDate.now();
        long late = today.isAfter(l.getDueDate())
                ? java.time.temporal.ChronoUnit.DAYS.between(l.getDueDate(), today)
                : 0;
        BigDecimal fine = new BigDecimal(late * 5);
        loanDao.markReturn(loanId, today, fine);
        loanDao.incAvailable(l.getBookId());
    }
}
