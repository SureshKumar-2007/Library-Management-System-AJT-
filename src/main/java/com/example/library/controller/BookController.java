package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired private BookService bookService;

    @GetMapping
    public String list(Model model, HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("books", bookService.list());
        return "books/list";
    }

    @GetMapping("/new")
    public String createForm(Model model, HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping
    public String create(@ModelAttribute Book book, HttpServletRequest req) {
        if (book.getAvailableCopies() <= 0) book.setAvailableCopies(book.getTotalCopies());
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model, HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("book", bookService.get(id));
        return "books/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.update(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
