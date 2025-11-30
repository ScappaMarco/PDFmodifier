package com.PDFmodifier.PDFmodifier.service.impl;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean doesUserUsernameAlreadyExists(String username, UserRepository userRepository) {
        User userToSearchByUsername = userRepository.getUserByUsername(username);
        return userToSearchByUsername != null;
    }

    @Override
    public boolean isUserRegistered(String email, UserRepository userRepository) {
        return userRepository.getUserByEmail(email) != null;
    }
}