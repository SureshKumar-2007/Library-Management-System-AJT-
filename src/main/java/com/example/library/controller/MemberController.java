package com.example.library.controller;

import com.example.library.model.Member;
import com.example.library.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired private MemberService memberService;

    @GetMapping
    public String list(Model model, HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("members", memberService.list());
        return "members/list";
    }

    @GetMapping("/new")
    public String createForm(Model model, HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", new Member());
        return "members/form";
    }

    @PostMapping
    public String create(@ModelAttribute Member member) {
        memberService.add(member);
        return "redirect:/members";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model, HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", memberService.get(id));
        return "members/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Member member) {
        member.setId(id);
        memberService.update(member);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        memberService.delete(id);
        return "redirect:/members";
    }
}
