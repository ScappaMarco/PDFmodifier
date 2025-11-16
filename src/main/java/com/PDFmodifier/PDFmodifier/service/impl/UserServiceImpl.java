package com.PDFmodifier.PDFmodifier.service.impl;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean doesUserEmailAlreadyExists(User user, UserRepository userRepository) {
        User userToSearchByEmail = userRepository.getUserByEmail(user.getEmail());
        return userToSearchByEmail != null;
    }

    @Override
    public boolean doesUserUsernameAlreadyExists(User user, UserRepository userRepository) {
        User userToSearchByUsername = userRepository.getUserByUsername(user.getUsername());
        return userToSearchByUsername != null;
    }

    @Override
    public boolean isUSerRegistered(User user) {
        return false;
    }
}