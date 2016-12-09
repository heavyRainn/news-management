package com.epam.newsmanagement.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api("Security controller")
@Controller
public class NewsmanagementSecurityController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
