package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
                             @RequestParam("email") String email,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             RedirectAttributes redirectAttributes) {
        User userToSearchByEmail = userRepository.getUserByEmail(email);
        if(userToSearchByEmail != null) {
            redirectAttributes.addFlashAttribute("user_email_already_exists", true);
            return "redirect:/register";
        } else {
            User userToSearchByUsername = userRepository.getUserByUsername(username);
            if(userToSearchByUsername != null) {
                redirectAttributes.addFlashAttribute("user_username_already_exists", true);
                return "redirect:/register";
            }
        }
        User userToAdd = new User(username, email, password, LocalDate.now());
        userRepository.insertUser(userToAdd);

        return "redirect:/home";
    }
}