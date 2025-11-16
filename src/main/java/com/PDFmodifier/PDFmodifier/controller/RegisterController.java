package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import com.PDFmodifier.PDFmodifier.service.factory.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final UserService userService;

    public RegisterController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = UserServiceFactory.getUserService();
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

        if(this.userService.doesUserEmailAlreadyExists(new User(username, email, password, LocalDate.now()), userRepository)) {
            redirectAttributes.addFlashAttribute("user_email_already_exists", true);
            return "redirect:/register";
        }
        if(this.userService.doesUserUsernameAlreadyExists(new User(username, email, password, LocalDate.now()), userRepository)) {
            redirectAttributes.addFlashAttribute("user_username_already_exists", true);
            return "redirect:/register";
        }
        /*
        Double if statement to secure the right error message later in the register page
         */
        User userToAdd = new User(username, email, password, LocalDate.now());
        userRepository.insertUser(userToAdd);

        return "redirect:/home";
    }
}