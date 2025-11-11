package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.model.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired private BookDao bookDao;

    public List<Book> list() { return bookDao.findAll(); }
    public Book get(int id) { return bookDao.findById(id); }
    public void add(Book b) { bookDao.insert(b); }
    public void update(Book b) { bookDao.update(b); }
    public void delete(int id) { bookDao.delete(id); }
}
