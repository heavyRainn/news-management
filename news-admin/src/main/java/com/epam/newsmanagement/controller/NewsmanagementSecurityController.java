package com.epam.newsmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsmanagementSecurityController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

}
