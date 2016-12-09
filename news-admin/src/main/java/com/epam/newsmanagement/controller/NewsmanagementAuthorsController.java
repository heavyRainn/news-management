package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.service.CrudService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api("Authors controller")
@Controller
@RequestMapping("/authors")
public class NewsmanagementAuthorsController {

    private static final String ITEMS_ON_PAGE = "itemsOnPage";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String SUCCESS = "success";

    private static final int ITEMS_ON_PAGINATION = 5;

    @Autowired
    private CrudService<Author> authorService;

    @GetMapping
    public String authorsPage(Model model) {
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, authorService.totalCount());
        model.addAttribute(ALL_AUTHORS, authorService.read(0, ITEMS_ON_PAGINATION));

        return "authors";
    }

    @GetMapping("/page-{pageNumber}")
    public String pagination(@PathVariable int pageNumber,
                             Model model) {
        pageNumber--;
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, authorService.totalCount());
        model.addAttribute(ALL_AUTHORS, authorService.read(ITEMS_ON_PAGINATION * pageNumber + 1, ITEMS_ON_PAGINATION * pageNumber + ITEMS_ON_PAGINATION));

        return "authors";
    }

    @PostMapping
    public String add(@RequestParam("name") String name,
                      @RequestParam("surname") String surname) {
        authorService.create(new Author(name, surname));
        return "redirect:/authors";
    }

    @ResponseBody
    @PostMapping("/update")
    public String update(Author author) {
        authorService.update(author);
        return SUCCESS;
    }

    @ResponseBody
    @PostMapping("/delete")
    public String delete(int id) {
        authorService.delete(id);
        return SUCCESS;
    }

}
