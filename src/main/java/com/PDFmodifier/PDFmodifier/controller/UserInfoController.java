package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class UserInfoController {

    private final UserRepository userRepository;

    public UserInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        //check if the user_id in the path is the same as the user logged. In case it is not we redirect to error
        Long sessionUserId = (Long) session.getAttribute("logged_user_id");
        if(!(id.equals(sessionUserId))) {
            return "redirect:/forbidden";
        }

        if(userRepository.getUserById(id) == null) {
            //404
            redirectAttributes.addFlashAttribute("user_not_found", true);
            return "redirect:/home";
        }

        model.addAttribute("user", userRepository.getUserById(id));

        return "user-info";
    }
}