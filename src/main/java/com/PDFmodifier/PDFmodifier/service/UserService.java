package com.PDFmodifier.PDFmodifier.service;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;

public interface UserService {
    public boolean doesUserUsernameAlreadyExists(String username, UserRepository userRepository);
    public boolean isUserRegistered(String email, UserRepository userRepository);
}