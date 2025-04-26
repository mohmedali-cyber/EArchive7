package com.example.EArchive7.controller;

import com.example.EArchive7.model.User;
import com.example.EArchive7.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        if (userService.isUsernameTaken(username)) {
            model.addAttribute("error", "اسم المستخدم موجود بالفعل");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // ممكن نضيف تشفير بعدين
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Optional<User> userOpt = userService.findByUsername(username);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            session.setAttribute("username", username);
            return "redirect:/";
        } else {
            model.addAttribute("error", "بيانات الدخول غير صحيحة");
            return "login";
        }
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

