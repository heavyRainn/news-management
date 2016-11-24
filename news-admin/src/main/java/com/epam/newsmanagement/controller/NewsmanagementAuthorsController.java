package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addAuthors")
public class NewsmanagementAuthorsController {

    private static final int ITEMS_ON_CRUD_PAGE = 5;

    @Autowired
    CrudService<Author> authorService;

    @RequestMapping()
    public String addAuthorsPage(Model model) {

        model.addAttribute("itemsOnPage", ITEMS_ON_CRUD_PAGE);
        model.addAttribute("totalCount", authorService.totalCount());
        model.addAttribute("allAuthors", authorService.read(0, ITEMS_ON_CRUD_PAGE));

        return "addAuthors";
    }

    @RequestMapping("/page-{pageNumber}")
    public String addAuthorsPagination(@PathVariable int pageNumber,
                                       Model model) {

        pageNumber--;
        model.addAttribute("itemsOnPage", ITEMS_ON_CRUD_PAGE);
        model.addAttribute("totalCount", authorService.totalCount());
        model.addAttribute("allAuthors", authorService.read(ITEMS_ON_CRUD_PAGE * pageNumber + 1, ITEMS_ON_CRUD_PAGE * pageNumber + ITEMS_ON_CRUD_PAGE));

        return "addAuthors";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addAuthors(@RequestParam("name") String name,
                             @RequestParam("surname") String surname) {

        System.out.println("New Author : " + name + " , " + surname);

        return "home";
    }


}
