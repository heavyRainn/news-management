package com.epam.newsmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsmanagementSecurityController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
