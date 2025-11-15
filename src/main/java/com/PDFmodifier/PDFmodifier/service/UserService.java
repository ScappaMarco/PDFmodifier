package com.PDFmodifier.PDFmodifier.service;

import com.PDFmodifier.PDFmodifier.database.query.UserRepository;
import com.PDFmodifier.PDFmodifier.model.User;

public interface UserService {
    public boolean doesUserEmailAlreadyExists(User user, UserRepository userRepository);
    public boolean doesUserUsernameAlreadyExists(User user, UserRepository userRepository);
    public boolean isUSerRegistered(User user);
}
