package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String redirectToHome(HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("logged_user_username") == null) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if(session.getAttribute("logged_user_username") == null) {
            return "redirect:/login";
        } else {
            Long userId = (Long) session.getAttribute("logged_user_id");

            if(userId != null) {
                model.addAttribute("user", userRepository.getUserById(userId));
            }
            return "home";
        }
    }

    /*
    @GetMapping("*")
    public String fallback(HttpSession session) {
        if(session.getAttribute("logged_user_username") == null) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }

     */
}