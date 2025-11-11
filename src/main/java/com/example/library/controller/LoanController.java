package com.example.library.controller;

import com.example.library.service.BookService;
import com.example.library.service.MemberService;
import com.example.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.list());
        model.addAttribute("members", memberService.list());
        model.addAttribute("openLoans", loanService.listOpen());
        model.addAttribute("allLoans", loanService.listAll());
        return "loans/list"; // /WEB-INF/views/loans/list.jsp
    }

    @PostMapping("/issue")
    public String issue(@RequestParam Integer bookId, @RequestParam Integer memberId) {
        loanService.issue(bookId, memberId);
        return "redirect:/loans";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable Integer id) {
        loanService.returnBook(id);
        return "redirect:/loans";
    }
}
