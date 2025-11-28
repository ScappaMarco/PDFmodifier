package com.PDFmodifier.PDFmodifier.service;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;

public interface UserService {
    public boolean doesUserUsernameAlreadyExists(User user, UserRepository userRepository);
    public boolean isUserRegistered(String email, UserRepository userRepository);
}