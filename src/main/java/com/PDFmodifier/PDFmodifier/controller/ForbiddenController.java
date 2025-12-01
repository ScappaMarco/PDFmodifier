package com.PDFmodifier.PDFmodifier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForbiddenController {

    @GetMapping("/forbidden")
    public String forbiddenPage() {
        return "forbidden-page";
    }
}