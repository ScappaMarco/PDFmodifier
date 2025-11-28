package com.PDFmodifier.PDFmodifier.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToHome(HttpSession session) {
        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        } else {
            return "home";
        }
    }

    @GetMapping("*")
    public String fallback(HttpSession session) {
        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }
}