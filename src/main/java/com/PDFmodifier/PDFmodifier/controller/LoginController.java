package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import com.PDFmodifier.PDFmodifier.service.factory.UserServiceFactory;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final UserService userService;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = UserServiceFactory.getUserService();
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        if(session.getAttribute("loggedUser") != null) {
            return "redirect:/home";
        }
        return "/login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        if(!(this.userService.isUserRegistered(email, this.userRepository))) {
            redirectAttributes.addFlashAttribute("user_not_registered", true);
            return "redirect:/login";
        }

        User registredUser = this.userRepository.getUserByEmail(email);

        if(!(BCrypt.checkpw(password, registredUser.getPassword()))) {
            redirectAttributes.addFlashAttribute("wrong_password", true);
            redirectAttributes.addFlashAttribute("user_old_email", email);
            return "redirect:/login";
        }

        session.setAttribute("logged_user", registredUser.getUsername());

        return "redirect:/home";
    }
}