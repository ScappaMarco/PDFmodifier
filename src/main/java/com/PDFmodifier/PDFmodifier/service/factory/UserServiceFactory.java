package com.PDFmodifier.PDFmodifier.service.factory;

import com.PDFmodifier.PDFmodifier.service.impl.UserServiceImpl;

public class UserServiceFactory {
    public static UserServiceImpl getUserService() {
        return new UserServiceImpl();
    }
}