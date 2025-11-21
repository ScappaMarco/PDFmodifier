package com.PDFmodifier.PDFmodifier.service.impl;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;
import com.PDFmodifier.PDFmodifier.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
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
    public boolean isUserRegistered(User user, UserRepository userRepository) {
        return userRepository.getUserByEmail(user.getEmail()) != null;
    }

    @Override
    public boolean checkPassword(User user, UserRepository userRepository) {
        //Here I can be sure that userRepository.getEncodedPasswordByEmail is not null, since this method is called only when we have checked the user with the inserted email existed
        //Parameter user has the "password" field not hashed, this is the reason behind the BCrypt.hashpw(user.getPassword) before running equals
        return (userRepository.getEncodedUserPasswordByEmail(user.getEmail()).equals(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())));
    }
}