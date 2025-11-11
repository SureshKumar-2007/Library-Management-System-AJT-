package com.example.library.controller;

import com.example.library.dao.UserDao;
import com.example.library.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @Autowired private UserDao userDao;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = userDao.findByUsername(username);
        if (u != null && (u.getPassword().equals(password))) { // plain compare for starter
            HttpSession session = req.getSession(true);
            session.setAttribute("user", u);
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest req) {
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "dashboard";
    }
}
