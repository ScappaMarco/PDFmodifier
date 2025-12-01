package com.PDFmodifier.PDFmodifier.controller;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import com.PDFmodifier.PDFmodifier.service.factory.UserServiceFactory;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final UserService userService;

    public RegisterController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = UserServiceFactory.getUserService();
    }

    @GetMapping("/register")
    public String showRegister(HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("logged_user_username") != null) {
            redirectAttributes.addFlashAttribute("register_blocked", true);
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("birth_date") LocalDate birthDate,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password_confirm") String confirmPassword,
            RedirectAttributes redirectAttributes,
            Model model,
            HttpSession session) {

        if (this.userService.isUserRegistered(email, this.userRepository)) {
            redirectAttributes.addFlashAttribute("user_email_already_exists", true);
            redirectAttributes.addFlashAttribute("user_old_first_name", firstName);
            redirectAttributes.addFlashAttribute("user_old_last_name", lastName);
            redirectAttributes.addFlashAttribute("user_old_birth_date", birthDate);
            redirectAttributes.addFlashAttribute("user_old_username", username);
            return "redirect:/register";
        }

        if(this.userService.doesUserUsernameAlreadyExists(username, userRepository)) {
            redirectAttributes.addFlashAttribute("user_username_already_exists", true);
            redirectAttributes.addFlashAttribute("user_old_first_name", firstName);
            redirectAttributes.addFlashAttribute("user_old_last_name", lastName);
            redirectAttributes.addFlashAttribute("user_old_birth_date", birthDate);
            redirectAttributes.addFlashAttribute("user_old_email", email);
            return "redirect:/register";
        }

        if(password.length() < 8) {
            redirectAttributes.addFlashAttribute("password_too_short", true);
            redirectAttributes.addFlashAttribute("user_old_first_name", firstName);
            redirectAttributes.addFlashAttribute("user_old_last_name", lastName);
            redirectAttributes.addFlashAttribute("user_old_birth_date", birthDate);
            redirectAttributes.addFlashAttribute("user_old_email", email);
            redirectAttributes.addFlashAttribute("user_old_username", username);
            return "redirect:/register";
        }

        List<String> numbers = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

        if(numbers.stream().noneMatch(password::contains)) {
            redirectAttributes.addFlashAttribute("numbers_missing", true);
            redirectAttributes.addFlashAttribute("user_old_first_name", firstName);
            redirectAttributes.addFlashAttribute("user_old_last_name", lastName);
            redirectAttributes.addFlashAttribute("user_old_birth_date", birthDate);
            redirectAttributes.addFlashAttribute("user_old_email", email);
            redirectAttributes.addFlashAttribute("user_old_username", username);
            return "redirect:/register";
        }

        List<String> specialCharacters = new ArrayList<>(Arrays.asList("!", "#", "<", "=", ">", "$", "\"", "'", "(", ")", "/", "@",
                "-", ";", ":", "+", "*", "?", "^", "%", "&", ".", "[", "]", "\\", "_", "{", "}", "|", "~"));

        if(specialCharacters.stream().noneMatch(password::contains)) {
            redirectAttributes.addFlashAttribute("special_character_missing", true);
            redirectAttributes.addFlashAttribute("user_old_first_name", firstName);
            redirectAttributes.addFlashAttribute("user_old_last_name", lastName);
            redirectAttributes.addFlashAttribute("user_old_birth_date", birthDate);
            redirectAttributes.addFlashAttribute("user_old_email", email);
            redirectAttributes.addFlashAttribute("user_old_username", username);
            return "redirect:/register";
        }

        if(!(password.equals(confirmPassword))) {
            redirectAttributes.addFlashAttribute("password_does_not_coincide", true);
            redirectAttributes.addFlashAttribute("user_old_first_name", firstName);
            redirectAttributes.addFlashAttribute("user_old_last_name", lastName);
            redirectAttributes.addFlashAttribute("user_old_birth_date", birthDate);
            redirectAttributes.addFlashAttribute("user_old_email", email);
            redirectAttributes.addFlashAttribute("user_old_username", username);
            return "redirect:/register";
        }
        /*
        Multi if statement to secure the right error message later in the register page
         */
        User userToAdd = new User(firstName, lastName, birthDate, username, email, password, LocalDate.now());
        userRepository.insertUser(userToAdd);

        /*
        Here I am taking back the user just added because when adding a user the DB generated an id, and taking it back
        is useful to actually being able to use the generated id, since when we create the user to add to the DB, it is constructed without id parameter
         */

        //user WITH id parameter set
        User userFromDb = userRepository.getUserByEmail(email);

        //session logic
        session.setAttribute("logged_user_username", username);
        session.setAttribute("logged_user_id", userFromDb.getId());

        //adding the user to the model
        //model.addAttribute("user", userFromDb);

        return "redirect:/home";
    }
}