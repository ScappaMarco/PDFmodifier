package com.PDFmodifier.PDFmodifier.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToHome(HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("logged_user") == null) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if(session.getAttribute("logged_user") == null) {
            return "redirect:/login";
        } else {
            return "home";
        }
    }

    @GetMapping("*")
    public String fallback(HttpSession session) {
        if(session.getAttribute("logged_user") == null) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }
}