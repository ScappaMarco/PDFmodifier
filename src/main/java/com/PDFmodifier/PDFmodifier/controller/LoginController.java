package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import com.PDFmodifier.PDFmodifier.service.factory.UserServiceFactory;
import jakarta.servlet.http.HttpSession;
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
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        //Password field not hashed here!!!
        User user = new User(email, password);

        if(!(this.userService.isUserRegistered(user, this.userRepository))) {
            redirectAttributes.addFlashAttribute("user_not_registered", true);
            return "redirect:/login";
        }

        if(!(this.userService.checkPassword(user, this.userRepository))) {
            redirectAttributes.addFlashAttribute("wrong_password", true);
            return "redirect:/login";
        }

        User userFromDb = this.userRepository.getUserByEmail(user.getEmail());
        session.setAttribute("logged_user", userFromDb.getUsername());

        return "redirect:/home";
    }
}